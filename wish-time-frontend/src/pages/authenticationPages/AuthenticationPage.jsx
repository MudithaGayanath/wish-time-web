import SignInPage from "./SignInPage.jsx";
import SmallButton from "../../components/buttons/SmallButton.jsx";
import React from "react";
import SignUpPage from "./SignUpPage.jsx";

export default function AuthenticationPage() {
    const [activePage, setActivePage] = React.useState("signIn");
    const signIn = <SignInPage/>
    const signUP = <SignUpPage/>

    return (
        <div className="w-full h-screen flex flex-col   ">
                {/*  button group start   */}
                <div className="  w-full flex justify-center items-center h-[10%] bg-red-400">
                    <div className="w-1/2 grid grid-cols-2 gap-4">

                    <SmallButton text={"Sign In"} isActive={activePage == "signIn"} onClick={() => {setActivePage("signIn");}}/>
                    <SmallButton text={"Sign Up"} onClick={() => { setActivePage("signUp");}} isActive={activePage == "signUp"}/>
                    </div>
                </div>
                {/*  button group end  */}
        {/*    page start*/}
            <div className="h-[90%] w-full   flex justify-center items-center">
            {/*sign in page start*/}
                <div className={`${activePage == "signIn" ? " relative" : "absolute mr-[400px] transition-all opacity-0   duration-1000 "} w-1/3`}>
                    {signIn}
                </div>
            {/*sign in page end*/}
            {/*    sign up page start*/}
                <div className={`${activePage == "signUp" ? "  text-red-400 relative" : "absolute ml-[400px] transition-all opacity-0   duration-1000 "} `}>
                    {signUP}
                </div>
            {/*    sign up page end*/}
            </div>
        {/*    page end*/}
        </div>
    )
}