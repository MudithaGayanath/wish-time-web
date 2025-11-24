import axios, {HttpStatusCode} from "axios";

const instens = axios.create({
    baseURL: "http://localhost:8080/api/v1",
    timeout: 5000
});

instens.interceptors.request.use((config) => {
    const token = localStorage.getItem("token");
    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
})

instens.interceptors.response.use((response) => {
        return response;
    },
     async (error) => {
        if (error.response.status === HttpStatusCode.Unauthorized) {
            console.log("response is unauthorized");
            if (localStorage.getItem("token")) {
            await localStorage.removeItem("token");
            }
        }
        return Promise.reject(error);
    });

export function getApi() {
    return instens;
}

