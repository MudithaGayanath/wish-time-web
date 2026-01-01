import MainHeading from "../../components/headings/MainHeading.jsx";
import Paragraph from "../../components/headings/Paragraph.jsx";
import TextField from "../../components/forms/TextField.jsx";
import Checkbox from "../../components/forms/Checkbox.jsx";
import {Link} from "react-router";
import PostButton from "../../components/buttons/PostButton.jsx";

function SignInPage() {
    return (
        <div className="w-1/2 h-fit flex-col flex justify-center items-center shadow-2xl rounded-2xl">
            {/*    title start*/}
            <MainHeading title="Sign In"/>
            {/*    title end*/}
            {/*    small text start*/}
            <div className="text-center">
                <Paragraph text="Welcome to the Wish Time Web App!"/>
                <Paragraph text="Plase sign in to your account."/>
            </div>
            {/*    small text end*/}
            {/* form start */}
            <div className=" grid grid-cols-1 p-8 w-full">
                   <TextField
                       value={"sdf"}
                       type={"text"}
                       id={"username"}
                       label={"Username"}
                   />
                <TextField
                       value={"sdf"}
                       type={"password"}
                       id={"password"}
                       label={"Password"}
                   />
                <div className="flex justify-between">
                    <Link to={"/forget_password"}>Forget Password</Link>
                    <Checkbox title="Remember me" />
                </div>
               <div className={"mt-3"}>
                   <PostButton
                       title={"Sign In"}
                       onClickFunction={()=>{}}
                   />
               </div>
            </div>
            {/* form end */}
        </div>
    );
}

export default SignInPage;