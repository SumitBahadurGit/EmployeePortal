export class Login {
    
    private loginId: string;
    private userName: string;
    private oldPw: string;
    private pw: string;
    private eid: string;        
    private status: string;
    private attempts: string; 
    public userRole : string;

    constructor(){

    }  

    getUserName(){
        return this.userName;
    }

    getPassWord(){
        return this.pw;
    }

    getEid(){
        return this.getEid;
    }

    setUserName(val : string){
        this.userName = val;
    }

    setPassWord(val : string){
        this.pw = val;
    }

    setEid(val : string){
        this.eid = val;
    }

    public getUserRole(){
        return this.userRole;
    }

    public setUserRole(role : string){
        this.userRole = role;
    }

  
}