import {getApi} from "../api/api.js";

const api = getApi();

export async function getAllUserStatus() {
    try{
        const response = await api.get("/userStatus/getAll");
        console.log("response ", response);
    }catch (err){
        console.log(err)
    }
}