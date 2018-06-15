// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2015/11/22 11:31:37
// Home Page: http://members.fortunecity.com/neshkov/dj.html http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   NdefMessage.java

package com.aofei.nfc.ndef;


// Referenced classes of package com.aofei.nfc.ndef:
//            NdefRecord

public class NdefMessage
{

    public NdefMessage(NdefRecord ndefRecords[])
    {
        this.ndefRecords = ndefRecords;
    }

    public NdefRecord[] getNdefRecords()
    {
        return ndefRecords;
    }

    private NdefRecord ndefRecords[];
}