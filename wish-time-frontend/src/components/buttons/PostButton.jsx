import React from "react";
import BaseButton from "./BaseButton";

function PostButton({title,onClickFunction}) {
  return (
    <BaseButton
      colorClass=" bg-blue-500 hover:bg-blue-600"
      onClickFunction={onClickFunction}
      title={title}
    />
  );
}

export default PostButton;
