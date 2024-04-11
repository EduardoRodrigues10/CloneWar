import React from "react";
import SidebarTitle from "./SidebarTitle";
import SidebarItems from "./SidebarItems";
import CircleColor from "./CircleColor";
import home from "../../assets/home.png";
import setting from "../../assets/setting.png";

function Sidebar() {
    const caca = () => {console.log("pd")}
    const Menu = [
        { title: "Dashboard", src: home },
        { title: "Setting", src: setting },
    ];

    return(
        <div className="flex">
            <div className="w-72 h-screen relative bg-gray-100 p-5 pt-8">
                <SidebarTitle/>
                <SidebarItems Menu={Menu} onClick={caca}/>
                <CircleColor/>
            </div>
        </div>
    )
}

export default Sidebar