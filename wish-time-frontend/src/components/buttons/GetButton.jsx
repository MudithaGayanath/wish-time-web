import React from "react";
import BaseButton from "./BaseButton";

function GetButton({title,onClickFunction}) {
   
  return (
    <BaseButton
      colorClass="bg-green-500 hover:bg-green-600"
      onClickFunction={onClickFunction}
      title={title}
    />
  );
}

export default GetButton;
