import MainHeading from "../../components/headings/MainHeading.jsx";
import SubHeading from "../../components/headings/SubHeading.jsx";
import TextField from "../../components/forms/TextField.jsx";
import PostButton from "../../components/buttons/PostButton.jsx";
import Checkbox from "../../components/forms/Checkbox.jsx";
import {useState} from "react";
import {signIn} from "./../../service/userService.js"
import {Bounce, toast, ToastContainer} from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import {useNavigate} from "react-router-dom";
import {createCookie} from "react-router";

const getCookie = (name) => {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop().split(";").shift();
    return null;
};

export default function SignInPage() {
    const [username,setUsername] = useState(getCookie("username") ? getCookie("username") : "");
    const [password,setPassword] = useState(getCookie("password") ? getCookie("password") : "");
    //errors
    const [usernameError,setUsernameError] = useState("");
    const [passwordError,setPasswordError] = useState("");
    const [credentialsError,setCredentialsError] = useState("");



    //navigation
    const navigate = useNavigate();

    function clear(){
        setUsernameError("");
        setPasswordError("");
    }

    async function  handleSignIn() {
        clear();
        const result = await signIn({
            username: username,password: password
        })

        if(!result.status){

            if(result.data.username){
                setUsernameError(result.data.username);
            }
            if(result.data.password){
                setPasswordError(result.data.password);
            }
            if(result.data.credentials){
                toast.error(result.data.credentials);
            }

        }else{
            toast.success("Login successful!");
            localStorage.setItem("token",result.data);
            if(document.getElementById("rm").checked){
            document.cookie = "username="+username;
            document.cookie = "password="+password;
            }
            // navigate("/home");
        }
    }
    return (
        <div className="    shadow-2xl rounded-2xl">
            <div className="  flex-row  ">
                {/*    heading start*/}
                <div className="text-center p-5">
                    <MainHeading title={"Welcome Back"}/>
                    <SubHeading title="Pleace sign in to your account"/>
                </div>
                {/*    heading end*/}
                {/*    form start*/}
                <div className="p-5  grid ">
                    <TextField
                        id={"username"}
                        label={"Username"}
                        error={usernameError}
                        type={"text"}
                        value={username}
                        onChange={(v) => { setUsername(v.target.value) }}
                    /> <TextField
                    id={"password"}
                    label={"Password"}
                    error={passwordError}
                    type={"password"}
                    value={password}
                    onChange={(v) => {setPassword(v.target.value)} }
                />
                    <Checkbox
                        title={"Remember me"}
                        id={"rm"}
                    />
                    <div>
                        <PostButton
                            title="Sign In"
                            onClickFunction={handleSignIn}
                        />
                    </div>

                </div>
                {/*    form end*/}
            </div>
        </div>
    )
}