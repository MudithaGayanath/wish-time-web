import axios from "axios";

const instens = axios.create({
    baseURL:"http://localhost:8080/api/v1",
    timeout:5000,
    // headers:{
    //     Authorization: "Bearer " + localStorage.getItem("token")
    // }
});

instens.interceptors.request.use((config) => {
    const token = localStorage.getItem("token");
    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
})

export function getApi(){return instens;}

