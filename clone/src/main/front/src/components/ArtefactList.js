import React, {useState} from "react";
import ArtefactService from "../service/ArtefactService";
import ListItem from "./ListItem";

const ArtefactList = () => {
    const [artefacts, setArtefacts] = useState([{
        id: 0,
        name: "test.jar"
    }, {
        id: 1,
        name: "caca.jar"
    }]);

    /*useEffect(async () => {
        ArtefactService.getArtefact()
            .then(res => res.json() )
            .then(data => setArtefacts(data))
            .catch((err) => { console.error(err) });
    }, []);*/

    return(
        <div className="flex flex-col">
            <div className="overflow-x-auto sm:-mx-6 lg:-mx-8">
                <div className="py-2 inline-block min-w-full sm:px-6 lg:px-8">
                    <div className="overflow-hidden">
                        <table className="min-w-full text-center">
                            <thead className="border-b">
                            <tr>
                                <th scope="col" className="text-sm font-medium text-gray-900 px-6 py-4">
                                    Id
                                </th>
                                <th scope="col" className="text-sm font-medium text-gray-900 px-6 py-4">
                                    Name
                                </th>
                                <th scope="col" className="text-sm font-medium text-gray-900 px-6 py-4">
                                    Status
                                </th>
                                <th scope="col" className="text-sm font-medium text-gray-900 px-6 py-4">
                                    Closest
                                </th>
                            </tr>
                            </thead>
                            {
                                artefacts.map( (artefact) => (
                                    <ListItem key={artefact.id} artefact={artefact}/>
                                ))
                            }
                        </table>
                    </div>
                </div>
            </div>
        </div>
    );
}

/*
{artefacts.map( (artefact) => (
                    <li key={artefact.id}>{artefact.name}</li>
                ) )}
 */

export default ArtefactList;