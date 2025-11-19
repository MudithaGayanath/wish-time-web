import React from "react";
import BaseButton from "./BaseButton";

function PutButton({title,onClickFunction}) {
  return (
    <BaseButton
      colorClass="bg-yellow-500 hover:bg-yellow-600 "
      onClickFunction={onClickFunction}
      title={title}
    />
  );
}

export default PutButton;
