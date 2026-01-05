import {getApi} from "../api/api.js";

const api = getApi();

export async function getAllUserStatus() {
    const rs = {
        status_code : 0,
        data : ""
    }
    try{
        const response = await api.get("/userStatus/getAll");
        rs.status_code = response.status;
        rs.data = response.data;
    }catch (err){
        console.log(err)
        rs.status_code = err.status;
    }
    return rs;
}