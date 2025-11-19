import axios from "axios";

const instens = axios.create({
    baseURL:"http://localhost:8080/api/v1",
    timeout:5000,
    // headers:{
    //     Authorization: "Bearer " + localStorage.getItem("token")
    // }
});

export function getApi(){return instens;}

