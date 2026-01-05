import {toast} from "react-toastify";

const tostProps = {
    position: "top-right",
    autoClose: 3000,
    type: "",
    draggable: true
}

export function successToast(message) {
    tostProps.type = "success";
    return toast(message,tostProps)
}
export function warningToast(message) {
    tostProps.type = "warning";
    return toast(message,tostProps)
}
export function errorToast(message) {
    tostProps.type = "error";
    return toast(message,tostProps)
}
