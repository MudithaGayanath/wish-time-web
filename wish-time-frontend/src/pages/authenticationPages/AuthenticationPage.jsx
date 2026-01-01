import SignInPage from "./SignInPage.jsx";
import SmallButton from "../../components/buttons/SmallButton.jsx";
import React, {useState} from "react";
import SignUpPage from "./SignUpPage.jsx";

export default function AuthenticationPage() {
    const [activePage, setActivePage] = useState(<SignInPage/>)

    function handleSignInPage() {
        setActivePage(<SignInPage/>)
    }
    function handleSignUpPage() {
        setActivePage(<SignUpPage/>)
    }

    return (
        <div className="w-full  h-screen  ">
        {/*   auth nav start*/}
            <div className=" w-full h-1/5 flex items-center justify-center gap-4">
                <button className="bg-purple-500 h-12 w-20  rounded-s-2xl text-white"
                onClick={handleSignInPage}>Sign In</button>
                <button
                onClick={handleSignUpPage}
                >Sign Up</button>
            </div>
        {/*   auth nav end*/}

        {/*    element start*/}
            <div className="w-full h-4/5 flex items-center justify-center">
                {activePage}
            </div>
        {/*    element end*/}
        </div>
    );
}