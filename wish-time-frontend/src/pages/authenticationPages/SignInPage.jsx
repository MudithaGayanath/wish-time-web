import MainHeading from "../../components/headings/MainHeading.jsx";
import Paragraph from "../../components/headings/Paragraph.jsx";
import TextField from "../../components/forms/TextField.jsx";
import Checkbox from "../../components/forms/Checkbox.jsx";
import {Link} from "react-router";
import PostButton from "../../components/buttons/PostButton.jsx";
import {signIn} from "../../service/userService.js";
import React, {useState} from "react";
import { successToast, warningToast} from "../../components/toasts/Toast.js";
import {useNavigate} from "react-router-dom";

function SignInPage() {
    const navigate = useNavigate();
    const [username, setUsername] = useState("muditha");
    const [usernameError, setUsernameError] = useState("");

    const [password, setPassword] = useState("Mudi1");
    const [passwordError, setPasswordError] = useState("");
    return (
        <div className="w-[90%] md:w-1/2 h-fit flex-col flex justify-center items-center shadow-2xl rounded-2xl">
            {/*    title start*/}
            <div className="p-4">
                <MainHeading title="Sign In"/>
            </div>
            {/*    title end*/}
            {/*    small text start*/}
            <div className="text-center">
                <Paragraph text="Welcome back to the Wish Time Web App!"/>
                <Paragraph text="Plase sign in to your account."/>
            </div>
            {/*    small text end*/}
            {/* form start */}
            <div className=" grid grid-cols-1 p-8 w-full">
                <TextField
                    id={"username"}
                    label={"Username"}
                    type={"text"}
                    value={username}
                    error={usernameError}
                    onChange={(e) => setUsername(e.target.value)}
                />
                <TextField
                    id={"password"}
                    label={"Password"}
                    type={"password"}
                    value={password}
                    error={passwordError}
                    onChange={(e) => setPassword(e.target.value)}
                />
                <div className="   flex   justify-between">
                    <Checkbox title="Remember me"/>
                    <Link to={"/forget_password"}>Forget Password?</Link>
                </div>
                <div className={"mt-3"}>
                    <PostButton
                        title={"Sign In"}
                        onClickFunction={async () => {
                            const rs = await signIn({username: username, password: password});
                            if (!rs.status) {

                                if (rs.data.username) {
                                    setUsernameError(rs.data.username);
                                }
                                if (rs.data.password) {
                                    setPasswordError(rs.data.password);
                                }
                                if (rs.data.auth) {
                                    warningToast(rs.data.auth)
                                }
                                if (rs.data.credentials) {
                                    warningToast(rs.data.credentials)
                                }

                            } else {
                            successToast("Sign In Successful!");
                            localStorage.setItem("token", rs.data);
                            setTimeout(() => {  navigate("/")}, 3000);
                            }
                        }
                        }
                    />
                </div>
            </div>
            {/* form end */}
        </div>
    );
}

export default SignInPage;