#!/usr/bin/perl
use strict;
use warnings;

use Data::Dumper;
use List::Util qw(max min any);
use List::UtilsBy qw(max_by);

# File import
my $file = "input.txt";
open my $input, '<', $file or die "can't open $file: $!";
chomp(my @lines = <$input>);
close $input or die "can't close $file: $!";

# Preparation
## Create a hash containing the letters following the key letter.
my @alphabet = ("A".."Z");
my %sequences;
foreach $_ (@lines) {
  my @order = order_from_string($_);
  if (exists($sequences{$order[0]})) {
    push(@{$sequences{$order[0]}}, $order[1])
  } else {
    my @arr = ($order[1]);
    $sequences{$order[0]} = \@arr;
  }
}
## Get the first char (one with larges amount of chars after it)
my $first;
my $longest = 0;
foreach my $key (keys(%sequences)) {
  my $len = scalar(@{$sequences{$key}});
  if ($len > $longest) {
      $first = $key;
      $longest = $len;
  }
}

# Solve the puzzle
## Part 1: BCIJFEDNGVXUMPSO not right answer
          #BCIJFEDNXUMPSO
#print Dumper(\%sequences);
foreach $_ (keys(%sequences)){
  print $_;
}
print "\n";
my %results;
get_next($first);
my %rev = reverse(%results);
foreach $_ (reverse(sort(keys(%rev)))) {
  print $rev{$_};
}


## Part 2:

# Subroutine declarations

sub order_from_string {
  my $in = shift;
  my @events = $in =~ /Step ([A-Z]) must be finished before step ([A-Z])/g;
  return @events;
}

sub get_next {
  my $curr = shift;
  return if (exists($results{$curr}));
  if (! exists($sequences{$curr})){
    $results{$curr} = 0;
    return;
  }
  for $_ (reverse(@{$sequences{$curr}})) {
    get_next($_);
  }
  $results{$curr} = max(values %results) + 1;
}
