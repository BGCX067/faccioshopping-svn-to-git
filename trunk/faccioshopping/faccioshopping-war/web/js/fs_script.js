/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//funzione che apre una finestra di popup per il carrello della spesa googlecheckout
//URL da visualizzare
//parametri della finestra di popup
function checkoutPopUp(URL,parametri)
{
    var fs_popup = null;
    if (confirm('Se avete un account Google il vostro ordine puo\' essere completato con Google checkout.\nContinuare?'))
    {
        fs_popup = window.open(URL, 'fs_popup', parametri);
        fs_popup.focus();
    }
}

//funzione che apre una finestra di popup
//URL da visualizzare
//parametri della finestra di popup
function windowPopUp(URL,parametri)
{
    var fswin_popup = null;
    fswin_popup = window.open(URL, 'fswin_popup', parametri);
    fswin_popup.focus();
}

