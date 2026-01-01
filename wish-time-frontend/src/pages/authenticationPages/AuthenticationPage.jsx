import SignInPage from "./SignInPage.jsx";
import SmallButton from "../../components/buttons/SmallButton.jsx";
import React from "react";
import SignUpPage from "./SignUpPage.jsx";

export default function AuthenticationPage() {
    return (
        <div className="w-full    ">
            <div className="grid grid-cols-5 h-screen bg-red-400">

                <div className="col-span-1"></div>
                <div className="col-span-3 flex justify-center items-center bg-white">
                    <SignInPage />
                </div>
                <div className="col-span-1"></div>

            </div>

        </div>
    );
}