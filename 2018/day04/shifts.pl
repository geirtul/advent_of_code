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
my $sleeps = calc_sleeptimes(@lines);
print Dumper($sleeps);
# Part 2:


# =========== Subroutine declarations ============

sub total_sleep {
  # Calculate the total amount of sleep for an agent
  # based on sleep/wake times in sleeptimes hash.
  my $arr_ref = shift;
  my %sleeptimes = %$arr_ref;
  
}

sub calc_sleeptimes {
  # Create a hash with Guard IDs as keys, and sleeping start/stop as values
  # by storing a reference to the array containing it.
  my @in = @_;
  # Match datetime, what happens, and guard ID.
  my $regex = qr/\[.+:(\d\d)\]\s(Guard|falls|wakes)\s(#\d+|asleep|up)/;
  my $current_id;
  my %sleeptimes;
  foreach $_ (@in) {
    my @match = $_ =~ $regex;
    my $time = $match[0];
    # If it's a guard event, set current id.
    if ($match[1] eq "Guard") {
      $current_id = $match[2];
      next; # Just need the ID, move on to data.
    }
    # if it's a sleep-even, append sleeptime.
    if ($match[1] eq "falls"){
      # Check if id exists in hash
      if (exists($sleeptimes{$current_id})) {
        push(@{$sleeptimes{$current_id}}, $time);
      } else {
        my @arr = $time;
        $sleeptimes{$current_id} = \@arr;
      }
    }
    # If it's a wakes-event, append waketime
    if ($match[1] eq "wakes"){
      if (exists($sleeptimes{$current_id})) {
        push(@{$sleeptimes{$current_id}}, $time);
      } else {
        my @arr = $time;
        $sleeptimes{$current_id} = \@arr;
      }
    }
  }
  return \%sleeptimes;
}
