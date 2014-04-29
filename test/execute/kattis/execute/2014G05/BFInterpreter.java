// EXT:CEQ


class BFInterpreter {

    public static void main(String[] args) {
        // Brainfuck interpreter
        int[] prog;
        int _;
        BrainfuckInterpreter bf;
        prog = new int[500];

        prog[0] = 62; // >
        prog[1] = 62; // >
        prog[2] = 43; // +
        prog[3] = 43; // +
        prog[4] = 43; // +
        prog[5] = 43; // +
        prog[6] = 43; // +
        prog[7] = 43; // +
        prog[8] = 43; // +
        prog[9] = 43; // +
        prog[10] = 91; // [
        prog[11] = 60; // <
        prog[12] = 43; // +
        prog[13] = 60; // <
        prog[14] = 43; // +
        prog[15] = 43; // +
        prog[16] = 43; // +
        prog[17] = 43; // +
        prog[18] = 43; // +
        prog[19] = 43; // +
        prog[20] = 43; // +
        prog[21] = 43; // +
        prog[22] = 43; // +
        prog[23] = 62; // >
        prog[24] = 62; // >
        prog[25] = 45; // -
        prog[26] = 93; // ]
        prog[27] = 62; // >
        prog[28] = 91; // [
        prog[29] = 45; // -
        prog[30] = 93; // ]
        prog[31] = 62; // >
        prog[32] = 91; // [
        prog[33] = 45; // -
        prog[34] = 93; // ]
        prog[35] = 62; // >
        prog[36] = 91; // [
        prog[37] = 45; // -
        prog[38] = 93; // ]
        prog[39] = 62; // >
        prog[40] = 91; // [
        prog[41] = 45; // -
        prog[42] = 93; // ]
        prog[43] = 62; // >
        prog[44] = 91; // [
        prog[45] = 45; // -
        prog[46] = 93; // ]
        prog[47] = 60; // <
        prog[48] = 60; // <
        prog[49] = 60; // <
        prog[50] = 60; // <
        prog[51] = 60; // <
        prog[52] = 60; // <
        prog[53] = 60; // <
        prog[54] = 91; // [
        prog[55] = 45; // -
        prog[56] = 62; // >
        prog[57] = 62; // >
        prog[58] = 43; // +
        prog[59] = 62; // >
        prog[60] = 43; // +
        prog[61] = 60; // <
        prog[62] = 60; // <
        prog[63] = 60; // <
        prog[64] = 93; // ]
        prog[65] = 62; // >
        prog[66] = 62; // >
        prog[67] = 62; // >
        prog[68] = 91; // [
        prog[69] = 45; // -
        prog[70] = 60; // <
        prog[71] = 60; // <
        prog[72] = 60; // <
        prog[73] = 43; // +
        prog[74] = 62; // >
        prog[75] = 62; // >
        prog[76] = 62; // >
        prog[77] = 93; // ]
        prog[78] = 60; // <
        prog[79] = 60; // <
        prog[80] = 91; // [
        prog[81] = 45; // -
        prog[82] = 62; // >
        prog[83] = 62; // >
        prog[84] = 43; // +
        prog[85] = 62; // >
        prog[86] = 43; // +
        prog[87] = 60; // <
        prog[88] = 60; // <
        prog[89] = 60; // <
        prog[90] = 93; // ]
        prog[91] = 62; // >
        prog[92] = 62; // >
        prog[93] = 62; // >
        prog[94] = 91; // [
        prog[95] = 45; // -
        prog[96] = 60; // <
        prog[97] = 32; //  
        prog[98] = 60; // <
        prog[99] = 60; // <
        prog[100] = 43; // +
        prog[101] = 62; // >
        prog[102] = 62; // >
        prog[103] = 62; // >
        prog[104] = 93; // ]
        prog[105] = 60; // <
        prog[106] = 60; // <
        prog[107] = 91; // [
        prog[108] = 62; // >
        prog[109] = 91; // [
        prog[110] = 62; // >
        prog[111] = 62; // >
        prog[112] = 62; // >
        prog[113] = 62; // >
        prog[114] = 91; // [
        prog[115] = 45; // -
        prog[116] = 93; // ]
        prog[117] = 60; // <
        prog[118] = 60; // <
        prog[119] = 60; // <
        prog[120] = 60; // <
        prog[121] = 60; // <
        prog[122] = 91; // [
        prog[123] = 45; // -
        prog[124] = 62; // >
        prog[125] = 62; // >
        prog[126] = 43; // +
        prog[127] = 62; // >
        prog[128] = 62; // >
        prog[129] = 62; // >
        prog[130] = 43; // +
        prog[131] = 60; // <
        prog[132] = 60; // <
        prog[133] = 60; // <
        prog[134] = 60; // <
        prog[135] = 60; // <
        prog[136] = 93; // ]
        prog[137] = 62; // >
        prog[138] = 62; // >
        prog[139] = 91; // [
        prog[140] = 45; // -
        prog[141] = 60; // <
        prog[142] = 60; // <
        prog[143] = 43; // +
        prog[144] = 62; // >
        prog[145] = 62; // >
        prog[146] = 93; // ]
        prog[147] = 60; // <
        prog[148] = 91; // [
        prog[149] = 45; // -
        prog[150] = 60; // <
        prog[151] = 45; // -
        prog[152] = 62; // >
        prog[153] = 62; // >
        prog[154] = 43; // +
        prog[155] = 60; // <
        prog[156] = 60; // <
        prog[157] = 91; // [
        prog[158] = 62; // >
        prog[159] = 62; // >
        prog[160] = 45; // -
        prog[161] = 60; // <
        prog[162] = 60; // <
        prog[163] = 91; // [
        prog[164] = 45; // -
        prog[165] = 62; // >
        prog[166] = 62; // >
        prog[167] = 62; // >
        prog[168] = 43; // +
        prog[169] = 60; // <
        prog[170] = 60; // <
        prog[171] = 60; // <
        prog[172] = 93; // ]
        prog[173] = 32; //  
        prog[174] = 93; // ]
        prog[175] = 62; // >
        prog[176] = 62; // >
        prog[177] = 62; // >
        prog[178] = 91; // [
        prog[179] = 45; // -
        prog[180] = 60; // <
        prog[181] = 60; // <
        prog[182] = 60; // <
        prog[183] = 43; // +
        prog[184] = 62; // >
        prog[185] = 62; // >
        prog[186] = 62; // >
        prog[187] = 93; // ]
        prog[188] = 60; // <
        prog[189] = 91; // [
        prog[190] = 45; // -
        prog[191] = 60; // <
        prog[192] = 91; // [
        prog[193] = 45; // -
        prog[194] = 93; // ]
        prog[195] = 62; // >
        prog[196] = 93; // ]
        prog[197] = 60; // <
        prog[198] = 93; // ]
        prog[199] = 60; // <
        prog[200] = 91; // [
        prog[201] = 60; // <
        prog[202] = 91; // [
        prog[203] = 45; // -
        prog[204] = 62; // >
        prog[205] = 62; // >
        prog[206] = 43; // +
        prog[207] = 62; // >
        prog[208] = 43; // +
        prog[209] = 60; // <
        prog[210] = 60; // <
        prog[211] = 60; // <
        prog[212] = 93; // ]
        prog[213] = 62; // >
        prog[214] = 62; // >
        prog[215] = 62; // >
        prog[216] = 91; // [
        prog[217] = 45; // -
        prog[218] = 60; // <
        prog[219] = 60; // <
        prog[220] = 60; // <
        prog[221] = 43; // +
        prog[222] = 62; // >
        prog[223] = 62; // >
        prog[224] = 62; // >
        prog[225] = 93; // ]
        prog[226] = 62; // >
        prog[227] = 62; // >
        prog[228] = 43; // +
        prog[229] = 60; // <
        prog[230] = 60; // <
        prog[231] = 60; // <
        prog[232] = 60; // <
        prog[233] = 91; // [
        prog[234] = 45; // -
        prog[235] = 62; // >
        prog[236] = 62; // >
        prog[237] = 43; // +
        prog[238] = 60; // <
        prog[239] = 60; // <
        prog[240] = 93; // ]
        prog[241] = 93; // ]
        prog[242] = 62; // >
        prog[243] = 62; // >
        prog[244] = 91; // [
        prog[245] = 45; // -
        prog[246] = 60; // <
        prog[247] = 60; // <
        prog[248] = 43; // +
        prog[249] = 32; //  
        prog[250] = 62; // >
        prog[251] = 62; // >
        prog[252] = 93; // ]
        prog[253] = 60; // <
        prog[254] = 93; // ]
        prog[255] = 60; // <
        prog[256] = 60; // <
        prog[257] = 91; // [
        prog[258] = 45; // -
        prog[259] = 62; // >
        prog[260] = 43; // +
        prog[261] = 62; // >
        prog[262] = 43; // +
        prog[263] = 60; // <
        prog[264] = 60; // <
        prog[265] = 93; // ]
        prog[266] = 62; // >
        prog[267] = 91; // [
        prog[268] = 45; // -
        prog[269] = 60; // <
        prog[270] = 43; // +
        prog[271] = 62; // >
        prog[272] = 93; // ]
        prog[273] = 62; // >
        prog[274] = 62; // >
        prog[275] = 62; // >
        prog[276] = 62; // >
        prog[277] = 62; // >
        prog[278] = 91; // [
        prog[279] = 45; // -
        prog[280] = 60; // <
        prog[281] = 60; // <
        prog[282] = 43; // +
        prog[283] = 60; // <
        prog[284] = 43; // +
        prog[285] = 62; // >
        prog[286] = 62; // >
        prog[287] = 62; // >
        prog[288] = 93; // ]
        prog[289] = 60; // <
        prog[290] = 60; // <
        prog[291] = 60; // <
        prog[292] = 91; // [
        prog[293] = 45; // -
        prog[294] = 60; // <
        prog[295] = 45; // -
        prog[296] = 62; // >
        prog[297] = 93; // ]
        prog[298] = 43; // +
        prog[299] = 60; // <
        prog[300] = 91; // [
        prog[301] = 62; // >
        prog[302] = 45; // -
        prog[303] = 60; // <
        prog[304] = 91; // [
        prog[305] = 45; // -
        prog[306] = 93; // ]
        prog[307] = 93; // ]
        prog[308] = 62; // >
        prog[309] = 91; // [
        prog[310] = 62; // >
        prog[311] = 91; // [
        prog[312] = 45; // -
        prog[313] = 93; // ]
        prog[314] = 62; // >
        prog[315] = 43; // +
        prog[316] = 60; // <
        prog[317] = 60; // <
        prog[318] = 45; // -
        prog[319] = 93; // ]
        prog[320] = 60; // <
        prog[321] = 60; // <
        prog[322] = 93; // ]
        prog[323] = 91; // [
        prog[324] = 45; // -
        prog[325] = 32; //  
        prog[326] = 93; // ]
        prog[327] = 62; // >
        prog[328] = 91; // [
        prog[329] = 45; // -
        prog[330] = 93; // ]
        prog[331] = 62; // >
        prog[332] = 62; // >
        prog[333] = 91; // [
        prog[334] = 45; // -
        prog[335] = 60; // <
        prog[336] = 60; // <
        prog[337] = 43; // +
        prog[338] = 62; // >
        prog[339] = 62; // >
        prog[340] = 93; // ]
        prog[341] = 62; // >
        prog[342] = 91; // [
        prog[343] = 45; // -
        prog[344] = 60; // <
        prog[345] = 60; // <
        prog[346] = 60; // <
        prog[347] = 60; // <
        prog[348] = 43; // +
        prog[349] = 62; // >
        prog[350] = 62; // >
        prog[351] = 62; // >
        prog[352] = 62; // >
        prog[353] = 93; // ]
        prog[354] = 62; // >
        prog[355] = 91; // [
        prog[356] = 45; // -
        prog[357] = 93; // ]
        prog[358] = 60; // <
        prog[359] = 91; // [
        prog[360] = 45; // -
        prog[361] = 93; // ]
        prog[362] = 60; // <
        prog[363] = 91; // [
        prog[364] = 45; // -
        prog[365] = 93; // ]
        prog[366] = 60; // <
        prog[367] = 91; // [
        prog[368] = 45; // -
        prog[369] = 93; // ]
        prog[370] = 60; // <
        prog[371] = 60; // <
        prog[372] = 60; // <
        prog[373] = 60; // <
        prog[374] = 46; // .
        prog[375] = 62; // >
        prog[376] = 46; // .
        prog[377] = 62; // >
        prog[378] = 46; // .


        bf = new BrainfuckInterpreter();
        _ = bf.setProgram(prog);
        _ = bf.run();
    }
}
class BrainfuckInterpreter {

    int[] prog;
    int[] jump;

    public int setProgram(int[] p) {
        LinkedListStack stack;
        int i;
        int instr;
        int _;
        prog = p;
        jump = new int[prog.length];
        stack = new LinkedListStack();
        // Create jump table
        i = 0;
        while (i < prog.length) {
            instr = prog[i];
            if (instr == 91) { // [
                _ = stack.push(i);
            } else if (instr == 93) { // ]               
                jump[i] = stack.pop();
                jump[jump[i]] = i;
            } else {
            }

            i = i + 1;
        }
        return 1;
    }

    public int run() {
        int[] mem;
        int pp;
        int mp;
        int instr;
        mem = new int[30000];
        pp = 0;
        mp = 0;
        while (pp < prog.length) {
            instr = prog[pp];
            if (instr == 43) { // +
                mem[mp] = mem[mp] + 1;
            } else if (instr == 45) { // -
                mem[mp] = mem[mp] - 1;
            } else if (instr == 60) { // <
                mp = mp - 1;
            } else if (instr == 62) { // >
                mp = mp + 1;
            } else if (instr == 91 && (mem[mp] == 0) == true) { // [
                pp = jump[pp];
            } else if (instr == 93 && (mem[mp] == 0) == false) { // ]
                pp = jump[pp];
            } else if (instr == 46) { // .
                System.out.println(mem[mp]);
            } else {
            }

            pp = pp + 1;
        }
        return 1;
    }
}

class LinkedListStack {

    LinkedListNode top;

    public int push(int i) {
        LinkedListNode newNode;
        int _;
        newNode = new LinkedListNode();
        _ = newNode.setNext(top);
        _ = newNode.setValue(i);
        top = newNode;
        return i;
    }

    public int pop() {
        int val;
        val = top.getValue();
        top = top.getNext();
        return val;
        
    }
}

class LinkedListNode {
    LinkedListNode next;
    int value;

    public int setNext(LinkedListNode n) {
        next = n;
        return 1;
    }

    public int setValue(int i) {
        value = i;
        return 1;
    }

    public int getValue() {
        return value;
    }

    public LinkedListNode getNext() {
        return next;
    }
}
