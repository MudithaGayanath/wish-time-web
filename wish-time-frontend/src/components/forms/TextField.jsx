import React from "react";

function TextField({
  label,
  helperText,
  placeholder,
  type,
  id,
  value,
  onChange,
  error,
  disabled
}) {
  return (
    <div className="mb-4 w-full ">
      {label && (
        <label htmlFor={id} className="block mb-1">
          {label}
          {helperText && (
            <small className=" text-gray-500 font-normal ms-2  ">
              {helperText}
            </small>
          )}
        </label>
      )}
      <input
        type={type}
        id={id}
        value={value}
        placeholder={placeholder}
        onChange={onChange}
        disabled={disabled}
        className={`w-full p-2 border rounded ${
          error ? "border-red-500" : "border-gray-300"
        } ${disabled ? "opacity-75 bg-gray-100 cursor-not-allowed" : ""}`}
      />
      {error && <p className="text-red-500 text-sm mt-1">{error}</p>}
    </div>
  );
}

export default TextField;
