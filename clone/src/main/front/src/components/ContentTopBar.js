import React, {useState} from "react";
import Dropzone from "./Dropzone";

function ContentTopBar(){
    const [sourceFile, setSources] = useState(null);
    const [classFile, setClasses] = useState(null);

    return(
        <>
            <h1>Home</h1>
            <Dropzone sources={sourceFile} classes={classFile} setSource={setSources} setClass={setClasses}/>
        </>
    );
}

export default ContentTopBar;