import SignInPage from "./SignInPage.jsx";
import SmallButton from "../../components/buttons/SmallButton.jsx";
import React from "react";

export default function AuthenticationPage() {
    const [activePage, setActivePage] = React.useState("signIn");
    const signIn = <SignInPage/>

    return (
        <div className="w-full h-screen flex  bg-green-300">
                {/*  button group start   */}
                <div className="  w-full flex justify-center items-center h-[10%] bg-red-400">
                    <div className="w-1/2 grid grid-cols-2 gap-4">

                    <SmallButton text={"Sign In"} isActive={activePage == "signIn"} onClick={() => {setActivePage("signIn");}}/>
                    <SmallButton text={"Sign Up"} onClick={() => { setActivePage("signUp");}} isActive={activePage == "signUp"}/>
                    </div>
                </div>
                {/*  button group end  */}
        </div>
    )
}