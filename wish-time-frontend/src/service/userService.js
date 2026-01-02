import {getApi} from './../api/api.js';
import {HttpStatusCode} from "axios";

const api = getApi();

export async function signIn(data) {
    const result = {
        status: false,
        data: "",
    };
    try {
        const response = await api.post("/auth/signIn", data);
        result.data = response.data.token;
        result.status = true
        return result;
    } catch (err) {
        if (err.status === HttpStatusCode.BadRequest) {
            result.data = err.response.data.errors;
        }
        return result;
    }
}

export async function signUp(data) {
    console.log("signUp");
    const result = {
        status: false,
        data: "",
    };
    console.log("result obj created");
    try {
        const response = await api.post("/auth/signUp", data);
        result.status = true
        return result;
    } catch (err) {
        if (err.status === HttpStatusCode.BadRequest) {
            console.log("Bad Request");
            result.data = err.response.data.errors;
        }
        return result;
    }
}

export async function getUserData(){
    const rs = {
        firstName: "",
        lastName: "",
        username: "",
        email:""
    }
    try{
        const response = await  api.get("/user/getUser");

    }catch(err){
        console.log(err);
    }
}


