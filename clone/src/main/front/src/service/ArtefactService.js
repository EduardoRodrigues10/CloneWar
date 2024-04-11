const ARTEFACT_URL_API = "http://localhost:8080/artefact"

class ArtefactService {
    async getArtefact(){
        return fetch(ARTEFACT_URL_API);
    }

    async postArtefact(formData){
        return fetch(ARTEFACT_URL_API, {
            method: 'post',
            body: formData
        });
    }
}

export default new ArtefactService();