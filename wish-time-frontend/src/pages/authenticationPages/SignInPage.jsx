import MainHeading from "../../components/headings/MainHeading.jsx";
import SubHeading from "../../components/headings/SubHeading.jsx";
import TextField from "../../components/forms/TextField.jsx";

export default function SignInPage(){
    return (
        <div className="    shadow-2xl rounded-2xl">
        <div className="  grid grid-rows-2  ">
        {/*    heading start*/}
            <div className="text-center">
                <MainHeading title={"Welcome Back"} />
                <SubHeading title="Pleace sign in to your account" />
            </div>
        {/*    heading end*/}
        {/*    form start*/}
            <div>
                <TextField
                id={"username"}
                label={"Username"}
                error={"Username is required"}
                type={"text"}
                value={"Muditha"}
                />
            </div>
        {/*    form end*/}
        </div>
        </div>
    )
}