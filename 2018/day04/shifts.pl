#!/usr/bin/perl
use strict;
use warnings;

use DateTime;
use Data::Dumper;
use List::Util ("none", "any");

my $file = "test.txt";
open my $input, '<', $file or die "can't open $file: $!";
chomp(my @lines = <$input>);
close $input or die "can't close $file: $!";


# =========== Solve the puzzle ============
# Part 1:
@lines = sort(@lines);
calc_sleeptime(@lines);
# Part 2:


# =========== Subroutine declarations ============

sub calc_sleeptimes {
  # Create a hash with Guard IDs as keys, and sleeping start/stop as values
  # by storing a reference to the array containing it.
  my @in = @_;
  # Match datetime, what happens, and guard ID.
  my $regex = qr/\[(.+)\]\s(Guard|falls|wakes)\s(#\d+|asleep|up)/;
  my $current_id;
  my %sleeptimes;
  foreach $_ (@in) {
    my @match = $_ =~ $regex;
    if ($match[1] eq "Guard") {
      $current_id = $match[1];
      if (exists($sleeptimes{$current_id}) {
        
      }
      next; # Just need the ID, move on to data.
    }
    # Get start or stop time for each event and store in array which ref is
    # in the hash
  }

}
