import MainHeading from "../components/headings/MainHeading.jsx";
import TextField from "../components/forms/TextField.jsx";
import PostButton from "../components/buttons/PostButton.jsx";
import React from "react";
import Image from "./../asset/image.png"

export default function AccountPage (){
    function handleProfileImageUpdate(){
      const imageFile =   document.getElementById("image");
      imageFile.click();
       const image = imageFile.value;
    }
    return(
        <div className="w-full h-screen">
        {/*    account details start*/}
            <div className="w-1/2 rounded-2xl shadow">
            {/*    title start*/}
                <MainHeading title="Account Details" />
            {/*    title end*/}
                {/* form start */}
                <div className=" grid grid-cols-1 p-8 w-full">
                    {/*image start*/}
                    <div className=" flex  justify-center items-center ">
                        <img src={Image}  id="imagePreview" className="w-40 h-40 hover:border hover:border-dashed hover:bg-gray-50 hover:border-gray-600 hover:rounded-lg"
                             onClick={handleProfileImageUpdate}
                             onMouseEnter={()=> document.getElementById("imageText").classList.remove("hidden")}
                        onMouseLeave={()=> document.getElementById("imageText").classList.add("hidden")}/>
                        <text className="absolute  hidden  " id="imageText">Change Image</text>
                        <input type={"file"}  className={"hidden"} id={"image"} />
                    </div>
                    {/*image end*/}
                    <div className="grid  md:grid-cols-2 gap-4">
                        <TextField
                            id={"firstName"}
                            label={"First Name"}
                            type={"text"}
                            // value={firstName}
                            // error={firstNameError}
                            // onChange={(e) => setFirstName(e.target.value)}
                        />
                        <TextField
                            id={"lastName"}
                            label={"Last Name"}
                            type={"text"}
                            // value={lastName}
                            // error={lastNameError}
                            // onChange={(e) => setLastName(e.target.value)}
                        />
                    </div>

                    <TextField
                        id={"email"}
                        label={"Email"}
                        type={"email"}
                        disabled
                        // value={email}
                        // error={emailError}
                        // onChange={(e) => setEmail(e.target.value)}
                    />
                    <TextField
                        id={"username"}
                        label={"Username"}
                        type={"text"}
                        disabled
                        // value={username}
                        // error={usernameError}
                        // onChange={(e) => {
                        //     setUsername(e.target.value)
                        // }}
                    />
                    <TextField
                        id={"password"}
                        label={"Password"}
                        type={"password"}
                        // value={password}
                        // error={passwordError}
                        // onChange={(e) => setPassword(e.target.value)}
                    />
                    <div className={"mt-3"}>
                        <PostButton
                            title={"Save Changes"}
                            onClickFunction={()=>{}}
                        />
                    </div>
                </div>
                {/* form end */}
            </div>
        {/*    account details end*/}
        </div>
    );
}