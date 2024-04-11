import React from "react";
import {useState} from "react";
import ArtefactService from "../service/ArtefactService";

const Dropzone = ({sources, classes, setSource, setClass}) => {
    const [artefactName, setArtefactName] = useState(null);
    const uploadHandler = async () => {
        if(!artefactName || artefactName === "" || !classes || !sources) { return; }

        const formData = new FormData();
        formData.append("artefactName", artefactName.toString());
        formData.append("sources", sources);
        formData.append("classes", classes);

        ArtefactService.postArtefact(formData).catch((err) => {
            console.error(err.message);
        });
    };

    return (
        <>
            <div className="flex justify-evenly py-4">
                <form>
                    <label className="block pb-4">Source Jar File</label>
                        <input type="file"
                               onChange={(e) => setSource(e.target.files[0])}
                               className="block w-full text-sm text-gray-500 file:mr-4 file:py-2 file:px-4 file:rounded-full file:border-0 file:text-sm file:font-semibold file:bg-blue-50 file:text-blue-700 hover:file:bg-blue-100"/>
                </form>
                <form>
                    <label className="block pb-4">Class Jar File</label>
                    <input type="file"
                           onChange={(e) => setClass(e.target.files[0])}
                           className="block w-full text-sm text-gray-500 file:mr-4 file:py-2 file:px-4 file:rounded-full file:border-0 file:text-sm file:font-semibold file:bg-blue-50 file:text-blue-700 hover:file:bg-blue-100"/>
                </form>
            </div>
            <div className="flex justify-evenly">
                <form className="max-w-sm">
                    <div className="flex border-b border-blue-600 py-2 my-4">
                        <input
                            onChange={(e) => setArtefactName(e.target.value)}
                            className="appearance-none bg-transparent border-none w-full text-gray-700 mr-3 py-1 px-2 leading-tight focus:outline-none"
                            type="text" placeholder="Artefact's name"/>
                        <button type="button"
                                onClick={uploadHandler}
                                className="inline-block px-6 py-2 border-2 border-blue-600 text-blue-600 font-medium text-xs leading-tight uppercase rounded-full hover:bg-black hover:bg-opacity-5 focus:outline-none focus:ring-0 transition duration-150 ease-in-out">Ajouter
                        </button>

                    </div>
                </form>
            </div>
        </>


    )

    /*return (

        <div className="flex h-1/4 items-center justify-center bg-grey-lighter">
            <label
                className="w-64 flex flex-col items-center px-4 py-6 bg-white text-red rounded-lg shadow-lg tracking-wide uppercase border border-red cursor-pointer hover:bg-red hover:text-white" >
                <svg className="w-8 h-8" fill="currentColor" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20">
                    <path
                        d="M16.88 9.1A4 4 0 0 1 16 17H5a5 5 0 0 1-1-9.9V7a3 3 0 0 1 4.52-2.59A4.98 4.98 0 0 1 17 8c0 .38-.04.74-.12 1.1zM11 11h3l-4-4-4 4h3v3h2v-3z"/>
                </svg>
                <span className="mt-2 text-base leading-normal">Select a file</span>
                <input onChange={uploadHandler} type='file' className="hidden"/>
            </label>
        </div>
    );*/
}

export default Dropzone