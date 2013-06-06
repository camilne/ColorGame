/*
 * Copyright 2013 Longarmx
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.longarmx.color;

import java.io.File;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;

public class MidiPlayer {

	Sequence sequence;
	Sequencer sequencer;
	Receiver receiver;

	private boolean loop = false;
	public boolean isPlaying = false;

	public MidiPlayer(String path) {
		try {
			sequence = MidiSystem.getSequence(new File(path));
			sequencer = getSequencer();
			sequencer.open();
			sequencer.setSequence(sequence);
			receiver = MidiSystem.getReceiver();
		} catch (MidiUnavailableException | InvalidMidiDataException
				| IOException e) {
			e.printStackTrace();
		}
	}
	
	private Sequencer getSequencer(){
		MidiDevice.Info[] midiInfo = MidiSystem.getMidiDeviceInfo();
		for(int i = 0; i< midiInfo.length; i++){
			if(midiInfo[i].getName().indexOf("Sequencer") != -1){
				System.out.println("Found sequencer: " + midiInfo[i].getName());
				//return (Sequencer) MidiSystem.getMidiDevice(midiInfo[i]);
			}
		}
		//System.err.println("Unable to find sequencer in device info...");
		try {
			return MidiSystem.getSequencer();
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		}
		System.err.println("System does not have sequencer...");
		return null;
	}

	public void start() {
		sequencer.stop();
		if (loop)
			sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
		sequencer.start();
		isPlaying = true;
	}

	public void setLooping(boolean loop) {
		this.loop = loop;
	}

	public void setVolume(float volume) {
		ShortMessage volumeMessage = new ShortMessage();
		for (int i = 0; i < 16; i++) {
			try {
				volumeMessage.setMessage(ShortMessage.CONTROL_CHANGE, i, 7,
						(int) (volume * 127));
				receiver.send(volumeMessage, -1);
			} catch (InvalidMidiDataException e) {
				e.printStackTrace();
			}
		}
	}

	public void stop() {
		sequencer.stop();
		isPlaying = false;
	}

	public void dispose() {
		stop();
		sequencer.close();
		receiver.close();
	}
}
