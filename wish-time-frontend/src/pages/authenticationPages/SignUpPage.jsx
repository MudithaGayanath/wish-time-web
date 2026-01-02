import MainHeading from "../../components/headings/MainHeading.jsx";
import Paragraph from "../../components/headings/Paragraph.jsx";
import TextField from "../../components/forms/TextField.jsx";
import Checkbox from "../../components/forms/Checkbox.jsx";
import {Link, useNavigation} from "react-router";
import PostButton from "../../components/buttons/PostButton.jsx";
import React, {useState} from "react";
import {signUp} from "./../../service/userService.js"
import {useNavigate} from "react-router-dom";
import {successToast} from "../../components/toasts/Toast.js";

function SignUpPage({navigation }) {


    const [firstName, setFirstName] = useState("");
    const [firstNameError, setFirstNameError] = useState("");

    const [lastName, setLastName] = useState("");
    const [lastNameError, setLastNameError] = useState("");

    const [email, setEmail] = useState("");
    const [emailError, setEmailError] = useState("");

    const [username, setUsername] = useState("");
    const [usernameError, setUsernameError] = useState("");

    const [password, setPassword] = useState("");
    const [passwordError, setPasswordError] = useState("");

    async function handleSignUp() {
        clearErrors();
        const rs = await signUp({
            firstName: firstName,
            lastName: lastName,
            userName: username,
            email: email,
            password: password,
        });
        if (!rs.status) {
            console.log(rs.data)
            if (rs.data.firstName) {
                setFirstNameError(rs.data.firstName);
            }

            if (rs.data.lastName) {
                setLastNameError(rs.data.lastName);
            }
            if (rs.data.username) {
                setUsernameError(rs.data.username);
            }
            if (rs.data.email) {
                setEmailError(rs.data.email);
            }
            if (rs.data.password) {
                setPasswordError(rs.data.password);
            }

        } else {
            successToast("Sign up successful");
            setTimeout(()=> navigation(),3000);
        }
    }

    function clearErrors() {
        setFirstNameError("");
        setLastNameError("");
        setEmailError("");
        setUsernameError("");
        setPasswordError("");
    }

    return (
        <div
            className="w-[90%] md:w-1/2 h-fit  mt-20 xl:mt-32 flex-col flex justify-center items-center shadow-2xl rounded-2xl">
            {/*    title start*/}
            <div className="p-4">
                <MainHeading title="Sign Up"/>
            </div>
            {/*    title end*/}
            {/*    small text start*/}
            <div className="text-center">
                <Paragraph text="Welcome to the Wish Time Web App!"/>
                <Paragraph text="Plase create an account for manage your daily tasks with Wish Time😉"/>
            </div>
            {/*    small text end*/}
            {/* form start */}
            <div className=" grid grid-cols-1 p-8 w-full">
                <div className="grid  md:grid-cols-2 gap-4">
                    <TextField
                        id={"firstName"}
                        label={"First Name"}
                        type={"text"}
                        value={firstName}
                        error={firstNameError}
                        onChange={(e) => setFirstName(e.target.value)}
                    />
                    <TextField
                        id={"lastName"}
                        label={"Last Name"}
                        type={"text"}
                        value={lastName}
                        error={lastNameError}
                        onChange={(e) => setLastName(e.target.value)}
                    />
                </div>

                <TextField
                    id={"email"}
                    label={"Email"}
                    type={"email"}
                    value={email}
                    error={emailError}
                    onChange={(e) => setEmail(e.target.value)}
                />
                <TextField
                    id={"username"}
                    label={"Username"}
                    type={"text"}
                    value={username}
                    error={usernameError}
                    onChange={(e) => {
                        setUsername(e.target.value)
                    }}
                />
                <TextField
                    id={"password"}
                    label={"Password"}
                    type={"password"}
                    value={password}
                    error={passwordError}
                    onChange={(e) => setPassword(e.target.value)}
                />
                <div className={"mt-3"}>
                    <PostButton
                        title={"Sign Up"}
                        onClickFunction={handleSignUp}
                    />
                </div>
            </div>
            {/* form end */}
        </div>
    );
}

export default SignUpPage;