/*
 * The MIT License (MIT)
 *
 * Copyright (c) liachmodded <https://github.com/liachmodded>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.github.liachmodded.uhcreloaded.forge.commands;

import static com.github.liachmodded.uhcreloaded.forge.util.Misc.translate;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class CommandUhcHelp extends CommandBase {

    private static final String NAME = "uhchelp";
    private static final String USAGE = "/uhchelp <banrule/banguideline>";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public boolean checkPermission(MinecraftServer mcServer, ICommandSender sender) {
        return true;
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return USAGE;
    }

    @Override
    public void execute(MinecraftServer mcServer, ICommandSender sender, String[] args) throws CommandException {
        int len = args.length;
        if (len == 1) {
            String arg = args[0];
            if (arg.equalsIgnoreCase("banrule") || arg.equalsIgnoreCase("banguideline")) {
                sender.sendMessage(new TextComponentString(translate("commands.uhcreloaded.help1")));
                sender.sendMessage(new TextComponentString("http://www.reddit.com/r/uhccourtroom/wiki/banguidelines"));
            } else {
                sender.sendMessage(new TextComponentString(translate("commands.uhcreloaded.help1")));
                sender.sendMessage(new TextComponentString("http://www.reddit.com/r/ultrahardcore/wiki/playerfaq"));
            }
        }

        throw new UhcCommandException(translate("commands.uhcreloaded.error.unknown"));
    }
}
