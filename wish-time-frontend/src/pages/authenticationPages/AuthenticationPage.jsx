import SignInPage from "./SignInPage.jsx";
import SmallButton from "../../components/buttons/SmallButton.jsx";
import React from "react";
import SignUpPage from "./SignUpPage.jsx";

export default function AuthenticationPage() {
    return (
        <div className="w-full h-screen flex justify-center items-center ">
            <SignInPage />
        </div>
    );
}