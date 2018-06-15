// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2015/11/22 11:31:37
// Home Page: http://members.fortunecity.com/neshkov/dj.html http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Ndef.java

package com.aofei.nfc.ndef;

import com.aofei.nfc.ndef.decorder.ActionRecordDecoder;
import com.aofei.nfc.ndef.decorder.AndroidApplicationRecordDecoder;
import com.aofei.nfc.ndef.decorder.BluetoothOOBDataRecordDecoder;
import com.aofei.nfc.ndef.decorder.DirectNdefRecordDecoder;
import com.aofei.nfc.ndef.decorder.MimeRecordDecoder;
import com.aofei.nfc.ndef.decorder.SmartPosterDecoder;
import com.aofei.nfc.ndef.decorder.TextRecordDecoder;
import com.aofei.nfc.ndef.decorder.UriRecordDecoder;
import com.aofei.nfc.ndef.decorder.VCardRecordDecoder;
import com.aofei.nfc.ndef.encorder.ActionRecordEncoder;
import com.aofei.nfc.ndef.encorder.AndroidApplicationRecordEncoder;
import com.aofei.nfc.ndef.encorder.BluetoothOOBDataRecordEncoder;
import com.aofei.nfc.ndef.encorder.DirectNdefRecordEncoder;
import com.aofei.nfc.ndef.encorder.MimeRecordEncoder;
import com.aofei.nfc.ndef.encorder.SmartPosterRecordEncoder;
import com.aofei.nfc.ndef.encorder.TextRecordEncoder;
import com.aofei.nfc.ndef.encorder.UriRecordEncoder;
import com.aofei.nfc.ndef.encorder.VCardRecordEncoder;

// Referenced classes of package com.aofei.nfc.ndef:
//            NdefRecordEncoder, NdefRecordDecoder, NdefMessageEncoder, NdefMessageDecoder

public class Ndef
{

    public Ndef()
    {
    }

    public static NdefRecordDecoder getNdefRecordDecoder()
    {
        return ndefRecordDecoder;
    }

    public static NdefRecordEncoder getNdefRecordEncoder()
    {
        return ndefRecordEncoder;
    }

    public static NdefMessageDecoder getNdefMessageDecoder()
    {
        return ndefMessageDecoder;
    }

    public static NdefMessageEncoder getNdefMessageEncoder()
    {
        return ndefMessageEncoder;
    }

    private static NdefRecordEncoder ndefRecordEncoder;
    private static NdefRecordDecoder ndefRecordDecoder;
    private static NdefMessageEncoder ndefMessageEncoder;
    private static NdefMessageDecoder ndefMessageDecoder;

    static 
    {
        ndefRecordEncoder = new NdefRecordEncoder();
        ndefRecordDecoder = new NdefRecordDecoder();
        ndefMessageEncoder = new NdefMessageEncoder(ndefRecordEncoder);
        ndefMessageDecoder = new NdefMessageDecoder(ndefRecordDecoder);
        ndefRecordDecoder.addRecordDecoder(new SmartPosterDecoder());
        ndefRecordDecoder.addRecordDecoder(new TextRecordDecoder());
        ndefRecordDecoder.addRecordDecoder(new UriRecordDecoder());
        ndefRecordDecoder.addRecordDecoder(new ActionRecordDecoder());
        ndefRecordDecoder.addRecordDecoder(new VCardRecordDecoder());
        ndefRecordDecoder.addRecordDecoder(new AndroidApplicationRecordDecoder());
        ndefRecordDecoder.addRecordDecoder(new BluetoothOOBDataRecordDecoder());
        ndefRecordDecoder.addRecordDecoder(new MimeRecordDecoder());
        ndefRecordDecoder.addRecordDecoder(new DirectNdefRecordDecoder());
        ndefRecordEncoder.addRecordEncoder(new SmartPosterRecordEncoder());
        ndefRecordEncoder.addRecordEncoder(new TextRecordEncoder());
        ndefRecordEncoder.addRecordEncoder(new UriRecordEncoder());
        ndefRecordEncoder.addRecordEncoder(new ActionRecordEncoder());
        ndefRecordEncoder.addRecordEncoder(new VCardRecordEncoder());
        ndefRecordEncoder.addRecordEncoder(new AndroidApplicationRecordEncoder());
        ndefRecordEncoder.addRecordEncoder(new BluetoothOOBDataRecordEncoder());
        ndefRecordEncoder.addRecordEncoder(new MimeRecordEncoder());
        ndefRecordEncoder.addRecordEncoder(new DirectNdefRecordEncoder());
    }
}