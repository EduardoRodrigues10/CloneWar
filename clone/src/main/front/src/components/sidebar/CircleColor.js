import React from "react";

const CircleColor = () => {
    return(
    <div className="w-full max-w-lg">
        <div
            className="absolute top-96 -left-12 w-72 h-72 bg-purple-200 rounded-full mix-blend-multiply filter blur-xl opacity-70"></div>
        <div
            className="absolute top-72 -right-4 w-72 h-72 bg-yellow-200 rounded-full mix-blend-multiply filter blur-xl opacity-70"></div>
    </div>)
}

export default CircleColor