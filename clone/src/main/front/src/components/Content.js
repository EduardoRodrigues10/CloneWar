import React, {Component} from "react";
import logo from "../assets/logo.png";
import PropTypes from "prop-types";
import Dropzone from "./Dropzone";
import ContentTopBar from "./ContentTopBar";
import ArtefactList from "./ArtefactList";

class Content extends Component{

    render() {
        const onFileChange = (files) => {
            console.log(files);
        };

        return (
            <div className="flex-col w-full h-screen justify-center items-center">
                <ContentTopBar/>
                <hr className="my-8 h-0.5 bg-blue-200 border-0 dark:bg-gray-700"/>
                <ArtefactList/>
            </div>
        );
    };
}


export default Content;