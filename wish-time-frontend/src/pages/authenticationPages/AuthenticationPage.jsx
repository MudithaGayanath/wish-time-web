import SignInPage from "./SignInPage.jsx";
import SmallButton from "../../components/buttons/SmallButton.jsx";
import React, {useState} from "react";
import SignUpPage from "./SignUpPage.jsx";

export default function AuthenticationPage() {
    const [activeElement, setActiveElement] = useState(<SignInPage/>);
    const [activePage, setActivePage] = useState("sign-in");

    function handleSignInPage() {
        setActiveElement(<SignInPage/>)
        setActivePage("sign-in");
    }

    function handleSignUpPage() {
        setActivePage("sign-up");
        setActiveElement(<SignUpPage navigation={() => {
            setActiveElement(<SignInPage/>)
        }}/>)
    }

    return (
        <div className="w-full  h-screen  ">
            {/*   auth nav start*/}
            <div className=" mt-4   w-full h-[10%] p-4  md:h-[5%] md:p-0   flex items-center justify-center gap-4">
                <div className="flex h-full items-center justify-center w-full md:w-1/2 shadow-2xl rounded-2xl ">
                    <SmallButton
                        text="Sign In"
                        isActive={activePage == "sign-in"}
                        onClick={handleSignInPage}
                    />
                    <SmallButton
                        text="Sign Up"
                        isActive={activePage == "sign-up"}
                        onClick={handleSignUpPage}
                    />

                </div>
            </div>
            {/*   auth nav end*/}

            {/*    element start*/}
            <div className=" w-full h-[90%] md:h-4/5   flex items-center justify-center">
                {activeElement}
            </div>
            {/*    element end*/}
        </div>
    );
}