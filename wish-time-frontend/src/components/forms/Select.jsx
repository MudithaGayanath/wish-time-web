import React from 'react'

function Select({ label,id, options, value, onChange, error,disabled }) {
    return (
      <div className="mb-4">
        {label && <label htmlFor={id} className="block mb-1">{label}</label>}
        <select key={id} disabled={disabled}
        id={id}
          value={value}
          onChange={onChange}
          className={`w-full p-2 border rounded ${
            error ? 'border-red-500' : 'border-gray-300' 
          }  ${disabled ?"opacity-75 bg-gray-100 cursor-not-allowed":""}`}
        >
          {options.map((option) => (
            
            <option key={option.value} value={option.value} selected={option.selected}
            // how to add here to if option is selected
            >
              {option.label}
            </option>
          ))}
        </select>
        {error && <p className="text-red-500 text-sm mt-1">{error}</p>}
      </div>
    );
  }

export default Select