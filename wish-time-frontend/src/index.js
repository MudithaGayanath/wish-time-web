import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import { createBrowserRouter } from "react-router";
import { RouterProvider } from "react-router/dom";
import MainHeading from "./components/headings/MainHeading.jsx";
import SubHeading from "./components/headings/SubHeading.jsx";
import GetButton from "./components/buttons/GetButton.jsx";

const router = createBrowserRouter([
    {
        path: "/",
        element: <>
            <MainHeading title={"sdfsd"} />
            <SubHeading title={"sdfsdf"} />
            <GetButton title={"get"} onClickFunction={()=>{}}/>
        </>,
    },
]);

const root = ReactDOM.createRoot(document.getElementById('root'));


root.render(
  <React.StrictMode>
      <RouterProvider router={router} />,
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
// reportWebVitals();
