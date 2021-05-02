function getCookie(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop().split(';').shift();
}

function getCryptKey(keyObject) {
    return CryptoJS.MD5(''+keyObject).toString();
}

function cryptUser(dataObject) {
	// var encrypted = CryptoJS.AES.encrypt("Message", "Secret Passphrase");
	// console.log('encrypted:', encrypted.toString());
	// var decrypted = CryptoJS.AES.decrypt(encrypted, "Secret Passphrase");
	// console.log('decrypted:', decrypted.toString(CryptoJS.enc.Utf8));
    let key = getCryptKey(dataObject.usuario_id);
    let data = JSON.stringify(dataObject);
    return CryptoJS.AES.encrypt(data, key).toString();
}

function decryptUser() {
    return JSON.parse(
        CryptoJS.AES.decrypt(
            sessionStorage.getItem("fastserviceData"), 
            getCookie("fastserviceId"))
        .toString(CryptoJS.enc.Utf8));
}