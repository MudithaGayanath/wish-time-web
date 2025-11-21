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
              className={`w-full h-12 p-2 border rounded ${
                  error ? "border-red-500" : "border-gray-300"
              } ${disabled ? "opacity-75 bg-gray-100 cursor-not-allowed" : ""}`}
          />
          {error && <p className="text-red-500 text-sm mt-1">{error}</p>}


          {/*<form className="max-w-md mx-auto">*/}
          {/*    <div className="relative z-0 w-full mb-5 group">*/}
          {/*        <input type="email" name="floating_email" id="floating_email"*/}
          {/*               className="block py-2.5 px-0 w-full text-sm text-heading bg-transparent border-0 border-b-2 border-default-medium appearance-none focus:outline-none focus:ring-0 focus:border-brand peer"*/}
          {/*               placeholder=" " required/>*/}
          {/*        <label htmlFor="floating_email"*/}
          {/*               className="absolute text-sm text-body duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:start-0 peer-focus:text-fg-brand peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6 rtl:peer-focus:translate-x-1/4 rtl:peer-focus:left-auto">{label}</label>*/}
          {/*    </div>*/}
          {/*    */}
          {/*</form>*/}

      </div>
  );
}

export default TextField;
