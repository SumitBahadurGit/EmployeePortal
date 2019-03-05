export class Login {
    
    public loginId: string;
    public userName: string;
    public oldPw: string;
    public pw: string;
    public eid: string;        
    public status: string;
    public attempts: string; 
    public userRole : string;

    constructor(){

    }  

    public getUserName(){
        return this.userName;
    }

    public getPassWord(){
        return this.pw;
    }

    public getEid(){
        return this.getEid;
    }

    public setUserName(val : string){
        this.userName = val;
    }

    public setPassWord(val : string){
        this.pw = val;
    }

    public setEid(val : string){
        this.eid = val;
    }

    public getUserRole(){
        return this.userRole;
    }

    public setUserRole(role : string){
        this.userRole = role;
    }

  
}