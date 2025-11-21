import React from "react";

function Checkbox({title, onChange,id}) {
  return (
    <div>
      <label className="flex items-center">
        <input
            id={id}
            onChange={onChange}
          type="checkbox"
          className="form-checkbox text-primary-600 rounded"
        />
        <span className="ml-2 text-gray-600">{title}</span>
      </label>
    </div>
  );
}

export default Checkbox;
