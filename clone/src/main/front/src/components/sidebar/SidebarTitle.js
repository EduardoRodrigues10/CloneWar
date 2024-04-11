import React from "react";
import logo from '../../assets/logo.png'

const SidebarTitle = () => {
    return (
        <div className="flex gap-x-4 items-center">
            <img
                src={logo}
                className={`cursor-pointer`}
                alt="image"/>

            <h1 className={`text-black origin-left text-left font-bold text-2xl`}>
                Clone<br/>War
            </h1>

        </div>
    )
}

export default SidebarTitle