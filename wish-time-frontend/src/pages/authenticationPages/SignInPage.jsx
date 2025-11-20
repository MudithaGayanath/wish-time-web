import MainHeading from "../../components/headings/MainHeading.jsx";
import SubHeading from "../../components/headings/SubHeading.jsx";
import TextField from "../../components/forms/TextField.jsx";
import PostButton from "../../components/buttons/PostButton.jsx";
import {signIn} from "../../service/service.js";
import Checkbox from "../../components/forms/Checkbox.jsx";

export default function SignInPage() {
    return (
        <div className="    shadow-2xl rounded-2xl">
            <div className="  flex-row  ">
                {/*    heading start*/}
                <div className="text-center p-5">
                    <MainHeading title={"Welcome Back"}/>
                    <SubHeading title="Pleace sign in to your account"/>
                </div>
                {/*    heading end*/}
                {/*    form start*/}
                <div className="p-5  grid ">
                    <TextField
                        id={"username"}
                        label={"Username"}
                        error={"Username is required"}
                        type={"text"}
                        value={"Muditha"}
                        onChange={(v) => {
                        }}
                    /> <TextField
                    id={"password"}
                    label={"Username"}
                    error={"Username is required"}
                    type={"text"}
                    value={"Muditha"}
                    onChange={(v) => {
                    }}
                />
                    <Checkbox
                        title={"Remember me"}
                    />
                    <div>
                        <PostButton
                            title="Sign In"
                            onClickFunction={() => {
                            }}
                        />
                    </div>

                </div>
                {/*    form end*/}
            </div>
        </div>
    )
}