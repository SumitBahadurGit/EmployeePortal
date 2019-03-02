export class Message{   

    errrorMessageQueue : string = null;
    successMessageQueue : string = null;
    errorMessage : string = null;
    successMessage : string = null;
    loadingMessage : string = null;

    private constructor(){        
        this.errorMessage = null;
        this.errrorMessageQueue = null;
        this.loadingMessage = null;
        this.successMessage = null;
        this.errorMessage = null;
    }

    static createLoadingMessage(message : string){
        var e = new Message();
        e.loadingMessage = message;
        return e;
    }
    static createErrorMessage(message : string){
        var e = new Message();
        e.errorMessage = message;
        return e;
    }
    static createSuccessMessage(message : string){
        var e = new Message();
        e.loadingMessage = message;
        return e;
    }
}