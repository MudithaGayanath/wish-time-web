import MainHeading from "../headings/MainHeading.jsx";
import React from "react";

export default function SideNavigation() {

    return (
        <div className="w-1/6 h-screen">
        {/*    title start*/}
            <div className="w-fit text-center">
                <MainHeading title="Wish" />
                <MainHeading title="Time" />
            </div>
        {/*    title end*/}
        </div>
    );
}