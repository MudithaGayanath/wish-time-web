import {getApi } from './../api/api.js';
import {HttpStatusCode} from "axios";

const api = getApi();
let token;
export async function signIn(data) {
    console.log("signIn");
    const result = {
      status:true,
      data:"",
    };
   try {
       const response = await api.post("/auth/signIn",data);
        result.data = response.data.token;
        return result;
   }catch(err) {
       if(err.status === HttpStatusCode.BadRequest){
           console.log("Bad Request");
           result.status = false
           result.data = err.response.data.errors;
       }
       return result;
   }
}

const signInData = {
    username:"Test_User_1",
    password:""
}
