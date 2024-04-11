import React from "react";

const ListItem = (props) => {
    const artefact = props.artefact;
    return (
        <tbody>
        <tr className="border-b bg-blue-100 border-blue-200">
            <td className="text-sm text-gray-900 font-medium px-6 py-4 whitespace-nowrap">
                {artefact.id}
            </td >
            <td className="text-sm text-gray-900 font-light px-6 py-4 whitespace-nowrap">
                {artefact.name}
            </td>
            <td className="text-sm text-gray-900 font-light px-6 py-4 whitespace-nowrap">
                READY
            </td>
            <td className="text-sm text-gray-900 font-light px-6 py-4 whitespace-nowrap">
            </td>
        </tr>
        </tbody>
    )
}

export default ListItem