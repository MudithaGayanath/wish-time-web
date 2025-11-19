import React from "react";
import BaseButton from "./BaseButton";

function DeleteButton({title,onClickFunction}) {
  return (
    <BaseButton colorClass="bg-red-500 hover:bg-red-600"
    onClickFunction={onClickFunction}
    title={title}
    />
  );
}

export default DeleteButton;
