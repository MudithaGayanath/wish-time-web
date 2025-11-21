import {getApi } from './../api/api.js';
import {HttpStatusCode} from "axios";

const api = getApi();
let token;
export async function signIn(data) {
    console.log("signIn");
    console.log(data);
    const result = {
      status:false,
      data:"",
    };
   try {
       const response = await api.post("/auth/signIn",data);
        result.data = response.data.token;
       result.status = true
        return result;
   }catch(err) {
       if(err.status === HttpStatusCode.BadRequest){
           console.log("Bad Request");
           result.data = err.response.data.errors;
       }
       return result;
   }
}


