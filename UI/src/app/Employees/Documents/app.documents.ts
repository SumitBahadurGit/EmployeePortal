import { Component, OnInit, Output, EventEmitter, ViewChild, Input } from '@angular/core';
import { PersonalInfo } from 'src/app/models/app.personalInfo';
import { WebService } from 'src/app/Service/app.webservice';
import { UpdateBus } from 'src/app/Service/app.updateBus';
import { Message } from 'src/app/models/app.message';
import { ErrorSuccessComponent } from 'src/app/Templates/app.ERR_SUC';
import { DocumentObj } from 'src/app/models/app.documentObj';
import { NULL_EXPR, THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { iif } from 'rxjs';
import { all } from 'q';
import { ActivatedRoute, Route } from '@angular/router';
import { LoggedUser } from 'src/app/Service/app.LoggedUser';

@Component({
  selector: 'documents',
  templateUrl: './app.documents.html',
  styleUrls: ['./app.documents.css'],
})


export class DocumentsComponent implements OnInit {


  @ViewChild(ErrorSuccessComponent)
  statusTemplate: ErrorSuccessComponent;


  type : string;

  FolderUtils : FolderUtils;
  // This is the tree that holds all folders/sub-folders
  mainFolder: CustomFolder;

  //This is the reference to the current sub-folder
  selectedFolder: CustomFolder = null;

  // These tracks the last folder for navigation
  folderTreeTracker: CustomFolder[];

  //Flags 
  isNewFileCreate: boolean = false;
  isNewFolderCreate: boolean = false;
  nodata: boolean = false;
  filesUploaded: boolean = false;
  filesDetelePrompt: boolean = false;
  isSelectPrompt: boolean = false;
  isProcessing: boolean = false;

  toggler: TogglePrompts = TogglePrompts.getTogglePrompts();

  defaultFolderName: string;
  defualtInitFileName: string = "init.readme";

  employeeId: string;
  employee: PersonalInfo;
  
  // is added before uploading the files
  filePrefix : string;

  //Stores the filenames downloaded
  downloadedFiles: CustomFile[];
  //Stores the filenames under selection
  selectedFiles: CustomFile[];
  selectedFolders: CustomFolder[];

  /************************ FOLDER NAVIGATION ************************************/

  disablePreviousEvents() {
    var anyPreviousPrompts = this.isSelectPrompt && this.isNewFileCreate
      && this.isNewFolderCreate && this.filesDetelePrompt;
    // Disable the previous prompts
    if (anyPreviousPrompts) {
      this.isSelectPrompt = false;
      this.isNewFolderCreate = false;
      this.isNewFileCreate = false;
      this.filesDetelePrompt = false;
      this.selectedFiles = null;
      this.selectedFolders = null;
    }
  }

  toggleValue(value: string) {
    this.toggler.toggle(value);

    if (!this.toggler.SELECT) {
      this.selectedFiles = null;
      this.selectedFolders = null;
    }
  }

  getLastThreeFolders() {
    if (this.folderTreeTracker != null) {
      if (this.folderTreeTracker.length < 4) {
        return this.folderTreeTracker;
      }
      else return this.folderTreeTracker.slice(this.folderTreeTracker.length - 3);
    }
  }

  isThisSelected(data: any) {

    if (data instanceof CustomFile) {
      if (this.selectedFiles != null && this.selectedFiles.length > 0) {
        return this.selectedFiles.includes(data);
      }
    } else if (data instanceof CustomFolder) {
      if (this.selectedFolders != null && this.selectedFolders.length > 0) {
        return this.selectedFolders.includes(data);
      }
    } else {
      return false;
    }
  }

  isAnyFilesSelected() {

    if (this.selectedFiles != null && this.selectedFiles.length > 0) {
      return true;
    }
    if (this.selectedFolders != null && this.selectedFolders.length > 0) {
      return true;
    }
    return false;

  }

  fileAction(data: any) {

    // Select Files
    if (this.toggler.SELECT) {

      if (data instanceof CustomFolder) {
        if (this.selectedFolders == null) {
          this.selectedFolders = [];
        }
        if (this.selectedFolders.includes(data)) {
          this.selectedFolders.splice(this.selectedFolders.indexOf(data), 1);
        } else {
          this.selectedFolders.push(data);
        }

      } else if (data instanceof CustomFile) {
        if (this.selectedFiles == null) {
          this.selectedFiles = [];
        }
        if (this.selectedFiles.includes(data)) {
          this.selectedFiles.splice(this.selectedFiles.indexOf(data), 1);
        } else {
          this.selectedFiles.push(data);
        }
      }
    } else if (this.toggler.DELETE) {
      if (data instanceof CustomFolder) {
        this.removeFolder([data]);
      } else if (data instanceof CustomFile) {
        this.removeFiles([data]);
      }
    } else if (!this.toggler.DELETE) {

      if (data instanceof CustomFolder) {
        this.openFolder(data);
      } else if (data instanceof CustomFile) {
      }

    }
  }

  // Is called when pressed on the delete button
  // Determines if there are any files in selection first
  deleteAction() {

    // If files were selected for deletion, delete 
    if (this.toggler.SELECT) {
      
      if (this.isAnyFilesSelected()) {
        if (this.selectedFiles != null && this.selectedFiles.length > 0) {
          this.removeFiles(this.selectedFiles);

        }

        if (this.selectedFolders != null && this.selectedFolders.length > 0) {
          this.removeFolder(this.selectedFolders);
        }
        // Clear the selected files
        this.selectedFiles = null;
        this.selectedFolders = null;

      } else  {
          this.statusTemplate.handleStatusMessage(Message.createErrorMessage("No files selected"));
      }
    } else {
      this.toggleValue(this.toggler.DELETE_STR);
    }

  }

  // Focuses on the element with the id
  focusThis(id: string) {
    document.getElementById(id).focus();
  }

  // Creates new file icon
  createNewFile() {
    this.toggler.toggle(this.toggler.FILE_ADD_STR);
    this.nodata = false;
  }

  // Creates new folder icon
  createNewFolder() {
    this.toggler.toggle(this.toggler.FOLDER_ADD_STR);
    this.nodata = false;
  }

  // Adds new File to the current dir
  addNewFile(fileName: string) {
    this.toggler.toggle(this.toggler.FILE_ADD_STR);
    var nf = this.getValidNewFileName(fileName);
    this.uploadEmptyFile(nf);
  }

  // Adds new Folder to the current dir
  addNewFolder(folderName: string) {
    this.toggler.toggle(this.toggler.FOLDER_ADD_STR);
    var nfm = this.getValidNewFolderName(folderName);
    this.uploadEmptyFolder(nfm);
  }

  // Avoids duplicates
  // No file-type check
  getValidNewFileName(fileName: string) {

    if (fileName == null) {
      fileName = "New File.txt";
    }

    var validFileName = fileName;

    if (this.mainFolder != null) {

      var created = false;
      var counter = 1;

      while (!created) {
        //If the folder exists, rename it 
        if (this.FolderUtils.findFileFromTree(validFileName, this.selectedFolder)) {
          validFileName = fileName.split(".")[0] + " (" + counter + ")" + "." + fileName.split(".")[1];
          counter++;
        } else {
          created = true;
        }
      }
    }

    return validFileName;
  }

  // Avoids duplicates
  getValidNewFolderName(folderName: string) {

    if (folderName == null) {
      folderName = "New Folder";
    }

    var validFolderName = folderName;

    if (this.mainFolder != null) {

      var created = false;
      var counter = 1;

      var newlyAddedFolder = null;
      while (!created) {
        //If the folder exists, rename it 
        if (this.FolderUtils.findFolderFromTree(validFolderName, this.selectedFolder)) {
          validFolderName = folderName + " (" + counter + ")";
          counter++;
        } else {
          created = true;
        }
      }
    }
    return validFolderName;
  }

  // Creates new folder
  uploadEmptyFolder(folderName: string) {
    try {
      this.isProcessing = true;
      var file = new File(["Empty file"], folderName + "/" + this.defualtInitFileName);
      var formData = new FormData;
      formData.append('file', file, this.getCompleteDir() +  file.name);
      this.uploadFiles(formData)
        .subscribe((data: any) => {
          console.log(data);
        },
          error => {
            console.log("ERROR: " + JSON.stringify(error));
            this.statusTemplate.handleStatusMessage(Message.createErrorMessage("There was an error creating new folder."));
            this.isProcessing = false;

          },
          () => {
            console.log(" UPLOAD SUCCESS");
            //  this.statusTemplate.handleStatusMessage(Message.createSuccessMessage("Successfully Uploaded."));
            this.isProcessing = false;
            this.reset();
          });
    } catch (error) {
      this.isProcessing = false;
    } finally {

    }

  }

  //Creates a new file
  uploadEmptyFile(fileName: string) {
    try {
      this.isProcessing = true;
      var file = new File(["Empty file"], fileName);
      var formData = new FormData;
      formData.append('file', file, this.getCompleteDir() + file.name);
      this.uploadFiles(formData)
        .subscribe((data: any) => {
          console.log(data);
        },
          error => {
            console.log("ERROR: " + JSON.stringify(error));
            this.statusTemplate.handleStatusMessage(Message.createErrorMessage("There was an creating new file."));
            this.isProcessing = false;

          },
          () => {
            console.log(" UPLOAD SUCCESS");
            this.isProcessing = false;
            this.reset();
          });
    } catch (error) {
      this.statusTemplate.handleStatusMessage(Message.createErrorMessage("There was an creating new file."));
      this.isProcessing = false;

    } finally {
    }

  }

  //Deletes the files
  removeFiles(files: CustomFile[]) {
    try {
      var updateObjs = [];
      if (files != null && files.length > 0) {

        files.forEach(file => {

          //loop and search for the DocumentObj
          if (this.employee.documnetObj != null) {
            this.employee.documnetObj.forEach(obj => {
              if (obj.docId == file.getFIleId()) {
                updateObjs.push(obj);
              }
            });
          }
        });

        // Update the db object with new properties
        if (updateObjs != null && updateObjs.length > 0) {
          this.isProcessing = true;

          this.deleteDocumentObj(updateObjs)
            .subscribe((data: any) => {
            },
              (error) => {
                console.log("ERROR deleteing: " + error);
                this.refresh();
                this.isProcessing = false;
                this.statusTemplate.handleStatusMessage(Message.createErrorMessage("Could not delete the file"));
              },
              () => {

                this.FolderUtils.deleteFilesFromTree(files, this.mainFolder);
                console.log("SUCCESSFULLY DELETED.");
                this.isProcessing = false;

              }
            );
        }
      }
    } catch (error) {
      this.statusTemplate.handleStatusMessage(Message.createErrorMessage("There was an error deleting file(s)."));
      this.isProcessing = false;

    } finally {
    }
  }

  //Deletes the folder
  removeFolder(folders: CustomFolder[]) {

    try {
      this.isProcessing = true;
      var files: CustomFile[] = [];
      if (folders != null && folders.length > 0) {
        folders.forEach(folder => {
          this.FolderUtils.getAllFiles(files, folder);
        });
      }

      if (files != null && files.length > 0) {
        this.isProcessing = true;
        var updateObjs: DocumentObj[] = [];
        files.forEach(file => {
          this.employee.documnetObj.forEach(obj => {
            if (obj.docId == file.getFIleId()) {
              updateObjs.push(obj);
            }
          });
        });

        if (updateObjs != null && updateObjs.length > 0) {
          this.deleteDocumentObjs(updateObjs)
            .subscribe((data: any) => {
            },
              (error) => {
                console.log("ERROR deleteing: " + error);
                this.isProcessing = false;
                this.refresh();
                this.statusTemplate.handleStatusMessage(Message.createErrorMessage("Could not delete the folder"));
              },
              () => {
                this.FolderUtils.deleteFoldersFromTree(folders, this.mainFolder);
                console.log("SUCCESSFULLY DELETED.")
                this.isProcessing = false;

              }
            );
        }
      } else {
        this.isProcessing = false;
        this.FolderUtils.deleteFoldersFromTree(folders, this.mainFolder);
        console.log("SUCCESSFULLY DELETED.")
      }
    } catch (error) {
      this.statusTemplate.handleStatusMessage(Message.createErrorMessage("Could not delete the folder(s)"));
      this.isProcessing = false;

    } finally {
    }
  }

  validateFileName(newFileName: string) {

    if (newFileName == null || newFileName.length == 0) {
      this.statusTemplate.handleStatusMessage(Message.createErrorMessage("File name cannot be empty."));
      return false;
    }

    if (!newFileName.includes(".")) {
      this.statusTemplate.handleStatusMessage(Message.createErrorMessage("Invalid file name. Please specify file type"));
      return false;
    }
    return true;
  }

  validateFolderName(newFolderName: string) {
    if (newFolderName == null || newFolderName.length < 1 || newFolderName.includes(".")) {
      return false;
    }
    return true;
  }

  updateFolderName(folderName: any, folder: CustomFolder) {

    if (this.validateFolderName(folderName)) {

      var files: CustomFile[] = [];

      this.FolderUtils.getAllFiles(files, folder);

      if (files != null && files.length > 0) {

        this.FolderUtils.updateFolderName(folderName, folder, files);

        var documentObjs = null;
        if (files != null && files.length > 0) {
          documentObjs = [];

          files.forEach(file => {
            var updateObj : DocumentObj = this.getObjFromFile(file);
            if (updateObj != null) {
              updateObj.fileName = file.getDir() + file.getFileName()
              documentObjs.push(updateObj);

            }
          });
        }
        if (documentObjs != null && documentObjs.length > 0) {

          this.updateDocumentObj(documentObjs)
            .subscribe((data: DocumentObj) => {
            },
              (error) => {
                console.log("ERROR Updating file " + error);
                this.statusTemplate.handleStatusMessage(Message.createErrorMessage("There was an error renaming file."));
                this.isProcessing = false;

              },
              () => {
                console.log("SUCCESSFULLY UPDATING FILE.")
                //  file.setFileName(newFileName);
                this.isProcessing = false;

              });
        }
      }
    }




  }

  //Updates the file name
  updateFileName(newFileName: any, file: CustomFile) {

    if (this.validateFileName(newFileName)) {
      try {
        this.isProcessing = true;

        var updateObj = this.getObjFromFile(file);

        // Update the db object with new properties
        if (updateObj != null) {

        // Update the complete file path
          file.setFileName(newFileName);
          updateObj.fileName = file.getDir() + file.getFileName();

          this.updateDocumentObj([updateObj])
            .subscribe((data: DocumentObj) => {
            },
              (error) => {
                console.log("ERROR Updating file " + error);
                this.statusTemplate.handleStatusMessage(Message.createErrorMessage("There was an error renaming file."));
                this.isProcessing = false;

              },
              () => {
                console.log("SUCCESSFULLY UPDATING FILE.")
                file.setFileName(newFileName);
                this.isProcessing = false;

              });

        }
      } catch (error) {
        this.statusTemplate.handleStatusMessage(Message.createErrorMessage("There was an error renaming file."));
        this.isProcessing = false;

      } finally {
      }
    } else { }
    this.isProcessing = false;
  }

  refresh() {
    this.isProcessing = true;
    setTimeout(() => this.ngOnInit(), 1000);
  }

  // Check the sub-folders name against the currently opened folders.
  openFolder(newFolder: CustomFolder, parentFolder?: CustomFolder) {
    if (newFolder != null) {
      // Opening a sub-folder
      if (parentFolder == null) {
        parentFolder = this.selectedFolder;
      }
      if (this.setSelectedFolder(newFolder.getFolderName(), parentFolder) == true) {
        this.selectedFolder = newFolder;
        this.setFoldersNavTracker(newFolder);
      }
    }
  }

  setSelectedFolder(folderName: string, parentFolder: CustomFolder): boolean {

    var found = this.FolderUtils.findFolderFromTree(folderName, parentFolder);
    if (found == null) {
      return false;
    } else {
      this.selectedFolder = found;
      return true;
    }
  }

  openPrevious() {
    var previousFolder = this.getPrevious();
    if (previousFolder != null) {
      this.openFolder(previousFolder, previousFolder);
    }
  }

  getPrevious(): CustomFolder {
    var previous = null;
    if (this.folderTreeTracker != null) {
      if (this.folderTreeTracker.length > 1) {
        //Remove the last added 
        var p = this.folderTreeTracker.pop();
      }

      // Open the last folder
      if (this.folderTreeTracker.length > 0) {
        previous = this.folderTreeTracker[this.folderTreeTracker.length - 1];
      }
    }
    return previous;
  }

  setFoldersNavTracker(_folder: CustomFolder) {

    if (this.folderTreeTracker == null) {
      this.folderTreeTracker = [];
    }

    if (this.folderTreeTracker.includes(_folder)) {
      var index = this.folderTreeTracker.indexOf(_folder);
      this.folderTreeTracker.splice(index + 1);
    } else {
      this.folderTreeTracker.push(_folder);
    }
  }

  /***************************************************************************************************/

  constructor(
    private dataService: WebService, private updateService: UpdateBus) {

  }

  fileSIze(size) {
    var s = Number(size / 1024).toFixed(2);
    return s + 'Kb';

  }

  getFolderOwner(){

     if(this.selectedFolder != null){
       return this.selectedFolder.getFolderOwnerId();
     } else {
       return this.employeeId;
     }
  }

  getCompleteDir() {
    var dir = this.filePrefix;

    if(this.selectedFolder != null){
      dir = dir + this.selectedFolder.getdir();
      dir = dir + this.selectedFolder.getFolderName();

    } else dir = dir + this.defaultFolderName;;

    if(dir != null && dir.length > 0){
      dir = dir + "/";
    }

    dir = dir.replace(new RegExp('\//'), '/');
    return  dir;
  }

  // This is called from the view and get the files to upload
  // No explicit call
  fileReadyToUpload(event) {

    try {
      this.isProcessing = true;
      //Upload flag
      this.filesUploaded = true;

      var files: File[] = [];
      var invalidFiles: File[] = [];

      for (var i = 0; i < event.target.files.length; i++) {
        var file = event.target.files[i];
        files.push(file);
      }

      if (files != null && files.length > 0) {
        var formData = new FormData;
        files.forEach(file => {

          var fileName = this.getValidNewFileName(file.name);

          if (this.validateFileName(fileName)) {

            formData.append('file', file, this.getCompleteDir() + fileName);
          } else {
            invalidFiles.push(file);
          }

        });

        if (invalidFiles != null && invalidFiles.length > 0) {
          this.statusTemplate.handleStatusMessage(Message.createErrorMessage("One or more files were invalid."));
        }

        this.uploadFiles(formData)
          .subscribe((data: any) => {
            console.log(data);
          },
            error => {
              console.log("ERROR: " + JSON.stringify(error));
              this.statusTemplate.handleStatusMessage(Message.createErrorMessage("There was an error uploading files."));
              this.isProcessing = false;

            },
            () => {
              console.log(" UPLOAD SUCCESS");
              this.isProcessing = false;

              this.reset();
            });

      }
    } catch (error) {
      this.statusTemplate.handleStatusMessage(Message.createErrorMessage("There was an error uploading files."));
      this.isProcessing = false;

    } finally {
    }

  }

  getFileType(fileName: string) {

    if (fileName != null && fileName.includes(".")) {
      return fileName.split(".")[1];
    }
    return "txt";
  }

  getObjFromFile(file: CustomFile) {

    var response = null;
    //loop and search for the DocumentObj
    if (this.employee.documnetObj != null) {
      this.employee.documnetObj.forEach(obj => {
        if (obj.docId == file.getFIleId()) {
          response = obj;
        }
      });
    }

    return response;

  }

  updateDocumentObj(updateObjs: DocumentObj[]) {

    return this.dataService.updateFiles<DocumentObj>(this.getFolderOwner(), updateObjs);

  }

  deleteDocumentObj(deleteObjs: DocumentObj[]) {
    return this.deleteDocumentObjs(deleteObjs);
  }

  deleteDocumentObjs(deleteObjs: DocumentObj[]) {

    return this.dataService.deleteDocs<any>(deleteObjs);

  }

  uploadFiles(formData: FormData) {

    return this.dataService
      .upload(formData, this.getFolderOwner());

  }

  reset() {
    this.filesUploaded = false;
    this.employee = null;
    this.ngOnInit();
  }

  // Downloads all files for this id
  downloadFiles() {

    if(this.type == "MASTER"){
      return this.dataService
      .getAllFiles<PersonalInfo>();

    } else if(this.type == "EMPLOYEE"){
      return this.dataService
      .getFilesById<PersonalInfo>(this.employeeId);

    }
    
      
  }

  ngOnInit() {

    this.type = this.updateService.getExpType();

    if(this.type=="MASTER"){
      //this.employeeId = this.updateService.getEmployeeDetail().employeeId;
      this.employeeId = LoggedUser.getUser().employeeId;
      this.defaultFolderName = "";
      this.filePrefix = "";
      this.FolderUtils = FolderUtils.getFolderUtils(this.defaultFolderName);
      
    } else if(this.type=="EMPLOYEE"){
      this.employeeId = this.updateService.getEmployeeDetail().employeeId;
      this.defaultFolderName = "";
      this.filePrefix = this.updateService.getEmployeeDetail().employeeId + "/";
//      this.defaultFolderName = this.employeeId;       
      this.FolderUtils = FolderUtils.getFolderUtils(this.defaultFolderName);

    }

    this.downloadFiles()
      .subscribe((resp: PersonalInfo) => this.employee = resp,
        error => {
          this.downloadedFiles = [];
          this.intitializeFolders();
          this.isProcessing = false;
        },
        () => {
          this.downloadedFiles = [];
          this.employee.documnetObj.forEach(d => {
            this.downloadedFiles.push(CustomFile.createNewFile(d.docId, d.employeeId, d.fileName, null, null));
          });
          this.intitializeFolders();
          this.isProcessing = false;
        }
      );
  }

  intitializeFolders() {

    // Populate the mainFolder with sub-folders if any.
    if (this.downloadedFiles != null && this.downloadedFiles.length > 0) {

      // Create the main folder tree once and dont loose reference to it.
      if (this.mainFolder == null) {
        this.mainFolder = CustomFolder.createNewFolder(this.defaultFolderName, null, LoggedUser.getUser().employeeId);
        this.selectedFolder = this.mainFolder;
        this.setFoldersNavTracker(this.selectedFolder);
      }

      
      // Add folders and files to the main folder.
      // This does not create new tree but add to the current tree
      for (var i = 0; i < this.downloadedFiles.length; i++) {
        this.FolderUtils.createFolderTree(this.mainFolder, null, this.downloadedFiles[i].getFileOwnderId(), this.downloadedFiles[i].getFIleId(),this.downloadedFiles[i].getFileName());
      }

      // Any files/folders in the past tree wont be lost ( Make sure there are not unwanted/residual files)     
      this.FolderUtils.refreshFolderTree(this.downloadedFiles, this.mainFolder);

      // Set the flag to false.
      this.nodata = false;

    } else {

      this.nodata = true;
      this.mainFolder = null;
      this.selectedFolder = null;
      this.folderTreeTracker = null;

    }
    console.log("*********** FOLDER TREE *****************");
    console.log(JSON.stringify(this.mainFolder));
    console.log("*****************************************");
  }
}

export class FolderUtils {

  private folderUtils: FolderUtils;
  private defaultFolderName: string;

  static getFolderUtils(defaultFolderName : string){
    return new FolderUtils(defaultFolderName)
  }
  private constructor(defaultFolderName : string){
      this.defaultFolderName = defaultFolderName;
  }

  public updateFolderName(folderName : string, folder : CustomFolder, childFiles? : CustomFile[]){

    if(folderName != null && folder != null){
      
        
        if(childFiles == null){
          this.getAllFiles(childFiles, folder);
        }

        if(childFiles != null){
  
          childFiles.forEach(file => {
            /** A/B/C.txt*/            
            var newFileDir = file.getDir().slice(folderName.length);
            newFileDir = folder.getdir() + folderName + "/";
            file.setDir(newFileDir);
          });
        }

        folder.setFolderName(folderName);        

    }
  }


  public getAllFileNames(fileName: string[], folder: CustomFolder, directoryName?: string) {

    if (folder != null && folder.getFiles() != null && folder.getFiles().length > 0) {
      folder.getFiles().forEach(f => {
        fileName.push(f.getDir() + f.getFileName());
      });
    }
    if (folder != null && folder.getSubFolders() != null) {
      folder.getSubFolders().forEach(sf => {
        this.getAllFileNames(fileName, sf, null);
      });
    }
  }

  public getAllFiles(files: CustomFile[], folder: CustomFolder, directoryName?: string) {

    if (folder != null && folder.getFiles() != null && folder.getFiles().length > 0) {
      folder.getFiles().forEach(f => {
        files.push(f);
      });
    }
    if (folder != null && folder.getSubFolders() != null) {
      folder.getSubFolders().forEach(sf => {
        this.getAllFiles(files, sf, null);
      });
    }
  }

  public deleteFoldersFromTree(folders: CustomFolder[], parent: CustomFolder, folderNames?: string[]) {

    if (folderNames == null) {
      folderNames = [];
      folders.forEach(folder => {
        folderNames.push(folder.getdir()+folder.getFolderName());
      });
    }

    if (parent != null
      && parent.getSubFolders() != null
      && parent.getSubFolders().length > 0) {

      var index = parent.getSubFolders().length;
      while (index--) {
        if (folderNames.includes(parent.getSubFolders()[index].getdir() + parent.getSubFolders()[index].getFolderName())) {
          parent.getSubFolders().splice(parent.getSubFolders().indexOf(parent.getSubFolders()[index]), 1);
        }
      }

    }
    if (parent != null) {
      var sf = parent.getSubFolders();
      if (sf != null) {
        for (var i = 0; i < sf.length; i++) {
          this.deleteFoldersFromTree(folders, sf[i], folderNames);
        }
      }
    }

  }

  public deleteFilesFromTree(files: CustomFile[], folder: CustomFolder, fileNames?: string[]) {
    if (fileNames == null) {
      fileNames = [];
      files.forEach(file => {
        fileNames.push(file.getDir()+file.getFileName());
      });
    }

    if (folder != null) {

      var index = folder.getFiles().length;
      while (index--) {
        if (fileNames.includes(folder.getFiles()[index].getDir() +
        folder.getFiles()[index].getFileName()         
        )) {
          folder.getFiles().splice(folder.getFiles().indexOf(folder.getFiles()[index]), 1);
        }
      }
    }

    if (folder != null && folder.getSubFolders() != null) {
      var subFolders = folder.getSubFolders();
      if (subFolders != null) {
        for (var i = 0; i < subFolders.length; i++) {
          this.deleteFilesFromTree(files, subFolders[i])
        }
      }
    }
  }

  public findFileFromTree(fileName: string, folder: CustomFolder) {

    var found: CustomFile = null;

    if (folder != null) {
      folder.getFiles().forEach(f => {
        if (f.getDir() + f.getFileName() == fileName) {
          found = f;
        }
      });
    }

    if (found == null) {
      if (folder != null && folder.getSubFolders() != null) {
        var subFolders = folder.getSubFolders();
        if (subFolders != null) {
          for (var i = 0; i < subFolders.length; i++) {
            if (found == null) {
              found = this.findFileFromTree(fileName, subFolders[i])
            }
          }
        }
      }
    }

    return found;
  }

  public findFolderFromTree(folderName: string, folder: CustomFolder) {
    var found = null;
    if (folderName == folder.getFolderName()) {
      found = folder;
    } else {
      if (folder != null && folder.getSubFolders() != null) {
        var subFolders = folder.getSubFolders();
        if (subFolders != null) {
          for (var i = 0; i < subFolders.length; i++) {
            if (found == null) {
              found = this.findFolderFromTree(folderName, subFolders[i])
            }
          }
        }
      }
    }
    return found;
  }

  public refreshFolderTree(files : CustomFile[], folder : CustomFolder, fileIds? : string[]){

    if(fileIds == null){
      fileIds = [];
      files.forEach(f => {
        fileIds.push(f.getFIleId());
      });
    }
      if(folder != null){

        if(folder.getFiles() != null){
          var index = folder.getFiles().length;
          while(index--){
            if(!fileIds.includes(folder.getFiles()[index].getFIleId())){
              folder.getFiles().splice(index,1);
            }
          }
        }

         if(folder.getSubFolders() != null){
           folder.getSubFolders().forEach(f =>{
            this.refreshFolderTree(files, f, fileIds);
           });
         }
      }

  }

  public createFolderTree(
    parentFolder: CustomFolder,
    folderName: string,
    ownerId : string,
    fileId: string,
    fileName: string) {

    var newParent : CustomFolder = null;
    // Folder name is empty the first time around.
    // It gets dynamically created from the filename 
    if(folderName != null){
      if(folderName == parentFolder.getFolderName() && 
      (parentFolder.getdir() == null || parentFolder.getdir() == "")){
        // Skip this.
        // We dont want to create duplicate parent folders/
      } else {
        if(parentFolder == null){
          throw({message : "Parent folder not found."});
        }
        //Check if the folder already exists. 
        newParent  = parentFolder.getSubFolders()
        .filter(f => f.getFolderName() == folderName)[0];
        
        // Create new folder
        if (newParent == null) {
          var dir = parentFolder.getdir();
    
          dir = dir + parentFolder.getFolderName()
    
          newParent = CustomFolder.createNewFolder(folderName, dir,ownerId);
          parentFolder.createSubFolder(newParent);
          }
      }
   }

   if(newParent == null){
      newParent = parentFolder;
   }
   
    // This is the folder name
    if (fileName.includes("/")) {

      var fileSplit = fileName.split("/");
      
      //Get the first part
      var newFolderName = fileSplit[0].trim();
      var newFileName = fileName.slice(newFolderName.length + 1);

      this.createFolderTree(
        newParent,       
        newFolderName,
        ownerId,
        fileId,      
        newFileName)

    } else if (fileName.includes(".")) {

     // var fileName = fileName.split(".")[0];
      var valid = true;

      //Avoid adding same files 
      newParent.getFiles().forEach(f => {
        if (f.getFIleId() == fileId) {
          valid = false;
        }
      });

      if (fileName == 'init.txt') {
        //valid = false;
      }

      if (valid) {
        var dir = newParent.getdir();        
        dir = dir + newParent.getFolderName();                

        var newFile = CustomFile.createNewFile(fileId, ownerId, fileName, null, dir);
        newParent.getFiles().push(newFile);
      }

    }
  }
}

export class TogglePrompts {

  public FILE_ADD: boolean = false;
  public FILE_DELETE: boolean = false;
  public FOLDER_ADD: boolean = false;
  public SELECT: boolean = false;
  public SELECTED: boolean = false;
  public DELETE: boolean = false;
  public DELETED: boolean = false;
  public REFRESH: boolean = false;

  public FILE_ADD_STR: string = "FILE_ADD";
  public FILE_DELETE_STR: string = "FILE_DELETE";
  public FOLDER_ADD_STR: string = "FOLDER_ADD";
  public SELECT_STR: string = "SELECT";
  public SELECTED_STR: string = "SELECTED";
  public DELETE_STR: string = "DELETE_STR";
  public DELETED_STR: string = "DELETED";
  public REFRESH_STR: string = "REFRESH_STR";

  //Toggle the field.
  // Only a single field can be true unless overrided
  toggle(value: string, override?: boolean) {

    if (value == this.FILE_ADD_STR) {
      var temp = !this.FILE_ADD;
      if (!override) this.defaultToFalse();
      this.FILE_ADD = temp;
    } else if (value == this.FILE_DELETE_STR) {
      var temp = !this.FILE_DELETE;
      if (!override) this.defaultToFalse();
      this.FILE_DELETE = temp;
    } else if (value == this.FOLDER_ADD_STR) {
      var temp = !this.FOLDER_ADD;
      if (!override) this.defaultToFalse();
      this.FOLDER_ADD = temp;
    } else if (value == this.SELECT_STR) {
      var temp = !this.SELECT;
      if (!override) this.defaultToFalse();
      this.SELECT = temp;
    } else if (value == this.SELECTED_STR) {
      var temp = !this.SELECTED;
      if (!override) this.defaultToFalse();
      this.SELECTED = temp;
    } else if (value == this.DELETE_STR) {
      var temp = !this.DELETE;
      if (!override) this.defaultToFalse();
      this.DELETE = temp;
    } else if (value == this.DELETED_STR) {
      var temp = !this.DELETED;
      if (!override) this.defaultToFalse();
      this.DELETED = temp;
    } else if (value == this.REFRESH_STR) {
      var REFRESH = !this.REFRESH;
      if (!override) this.defaultToFalse();
      this.REFRESH = temp;
    }
  }

  defaultToFalse() {
    this.FILE_ADD = false;
    this.FILE_DELETE = false;
    this.FOLDER_ADD = false;
    this.SELECT = false;
    this.SELECTED = false;
    this.DELETE = false;
    this.DELETED = false;
    this.REFRESH = false;
  }

  private constructor() {

  }

  static getTogglePrompts() {
    return new TogglePrompts();
  }
}

export class CustomFolder {

  private dir: string;
  private folderOwnerId : string;
  private folderName: string;
  private files: CustomFile[];
  private subFolders: CustomFolder[];

  setFolderId(folderId : string){
    this.folderOwnerId = folderId;
  }

  getFolderOwnerId(){
    return this.folderOwnerId;
  }
  setFolderName(folderName: string) {
    this.folderName = folderName;
  }

  setdir(dir : string){
    this.dir = dir;
  }

  getdir() {
    if(this.dir == null || this.dir.length == 0){
      return "";
    } else {
      return this.dir;
    }
  }

  getFolderName() {
    return this.folderName;
  }

  getFiles() {
    if(this.files == null){
      this.files = [];
    }
    return this.files;
  }

  getSubFolders() {
    if (this.subFolders == null) {
      this.subFolders = [];
    }
    return this.subFolders;
  }

  createSubFolders(folders: CustomFolder[]) {
    folders.forEach(f => {
      this.createSubFolder(f);
    });
  }

  createSubFolder(folder: CustomFolder) {
    this.getSubFolders().push(folder);
  }

  private constructor(folderName: string, _dir: string, folderOwnerId : string, files?: CustomFile[]) {
    this.folderName = folderName;
    if(_dir == null || _dir.length == 0){
      this.dir = "";
    } else {
      this.dir = _dir + "/";
    }
    this.files = [];
    this.subFolders = null;
    this.folderOwnerId = folderOwnerId;
  }

  static createNewFolder(folderName: string, dir: string, folderOwnerId : string) {
    return new CustomFolder(folderName, dir, folderOwnerId);
  }
}

export class CustomFile {

  private fileId : string;
  private fileOwnerId: string;
  private fileName: string;
  private fileType: FILETYPES;
  private dir: string;

  setFileOwnerId(id : string){
    this.fileOwnerId = id;
  }

  getFileOwnderId(){
    return this.fileOwnerId;
  }

  setDir(dir : string){
    this.dir = dir;
  }

  getFIleId(){
    return this.fileId;
  }
  
  getDir() {
    if(this.dir == null || this.dir.length == 0){
      return "";
    } else {
      return this.dir;
    }

  }
  getFileName() {
    return this.fileName;
  }
  getFileType() {
    return this.fileType;
  }

  setFileName(fileName: string) {
    this.fileName = fileName;
  }

  delete() {
    this.fileName = null;
    this.fileType = null;
    this.dir = null;
  }

  private constructor(fileId : string, fileOwnerId : string, fileName: string, fileType: FILETYPES, _dir: string) {
    this.fileId = fileId;
    this.fileOwnerId = fileOwnerId;
    this.fileName = fileName;
    this.fileType = fileType;
    if(_dir == null || _dir.length == 0){
      this.dir = "";
    } else {
      this.dir = _dir + "/";
    }
  }

  static createNewFile(fileId : string, fileOwnerId : string, fileName: string, fileType: FILETYPES, dir: string) {
    return new CustomFile(fileId, fileOwnerId, fileName, fileType, dir);
  }

}

export enum FILETYPES {
  TXT = "txt",
  PDF = "pdf",
  DOCX = "docx",
  JPEG = "jpeg",
  SQL = "sql",
  PNF = "png"
}