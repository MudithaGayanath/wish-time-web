import MainHeading from "../../components/headings/MainHeading.jsx";
import Paragraph from "../../components/headings/Paragraph.jsx";
import TextField from "../../components/forms/TextField.jsx";
import Checkbox from "../../components/forms/Checkbox.jsx";
import {Link} from "react-router";
import PostButton from "../../components/buttons/PostButton.jsx";

function SignUpPage() {
    return (
        <div className="w-[90%] md:w-1/2 h-fit flex-col flex justify-center items-center shadow-2xl rounded-2xl">
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
                <div className="grid grid-cols-2 gap-4">
                    <TextField
                        value={"sdf"}
                        type={"text"}
                        id={"first_name"}
                        label={"First Name"}
                    />
                    <TextField
                        value={"sdf"}
                        type={"text"}
                        id={"last_name"}
                        label={"Last Name"}
                    />
                </div>

                <TextField
                    value={"sdf"}
                    type={"email"}
                    id={"email"}
                    label={"Email"}
                />
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
                <div className={"mt-3"}>
                    <PostButton
                        title={"Sign Up"}
                        onClickFunction={() => {
                        }}
                    />
                </div>
            </div>
            {/* form end */}
        </div>
    );
}

export default SignUpPage;