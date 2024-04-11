import React, {Component} from "react";
import home from "../../assets/home.png"
import setting from "../../assets/setting.png"

class SidebarItems extends Component{
    constructor(props) {
        super(props);
        this.state = {
            current: 0
        }

        this.changeCurrent = this.changeCurrent.bind(this)
    }

    changeCurrent(index){
        this.setState({
            current: index
        }, () => {console.log(this.state.current)})
    }

    render(){
        const {Menu} = this.props;
        return (
            <ul className="pt-6">
                {Menu.map((Menu, index) => (
                    <li
                        key={index}
                        className={`flex rounded-md p-2 cursor-pointer hover:bg-white text-black text-sm items-center gap-x-4
                ${Menu.gap ? "mt-9" : "mt-2"} ${
                            index === this.state.current && "bg-white text-bold"
                        }`}
                        onClick={() => this.changeCurrent(index)}>

                        <img src={Menu.src} alt="image"/>
                        <span className={`origin-left`}>
                        {Menu.title}
                    </span>
                    </li>
                ))}
            </ul>
        )
    }
}


export default SidebarItems